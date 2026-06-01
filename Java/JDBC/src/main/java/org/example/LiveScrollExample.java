package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LiveScrollExample {

    // Shared connection between Thread A and Thread B
    private Connection con = null;

    public void findData() {

        Thread threadA = new Thread(() -> {
            try {
                // Initialize the connection
                con = DatabaseConnection.getInstance().getConnection();

                // Open a scroll-sensitive, updatable Statement
                try (Statement stmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                     ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

                    System.out.println("[Thread A] Reading initial records:");
                    while (rs.next()) {
                        System.out.println("[Thread A] ID: " + rs.getString("name") +
                                ", Name: " + rs.getString("email") );
                    }

                    // Give Thread B a couple of seconds to execute its update
                    Thread.sleep(3000);

                    // Since it's TYPE_SCROLL_SENSITIVE, moving backwards lets us see updates
                    System.out.println("\n[Thread A] Moving backward to detect updates...");
                    while (rs.previous()) {
                        System.out.println("[Thread A Backward] NAme: " + rs.getString("name") +
                                ", Email: " + rs.getString("email") );
                    }

                } // ResultSet and Statement automatically close here
            } catch (SQLException | InterruptedException e) {
                System.err.println("Database error in Thread A: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Ensure connection gets closed safely after both threads are likely done
                try {
                    if (con != null && !con.isClosed()) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                // Wait a moment for Thread A to establish the connection and read data first
                Thread.sleep(1000);

                if (con == null || con.isClosed()) {
                    System.out.println("[Thread B] Connection not available.");
                    return;
                }

                // Creating its own statement to avoid cursor conflicts on the same connection
                try (Statement stmtB = con.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                     ResultSet rsB = stmtB.executeQuery("SELECT * FROM users")) {

                    if (rsB.next()) {
                        System.out.println("\n[Thread B] Original Record: ID: " + rsB.getString("name") +
                                ", Email: " + rsB.getString("email"));

                        // Update the salary programmatically via the ResultSet

                        rsB.updateString("name", "John");
                        rsB.updateRow(); // Pushes the change to the database

                        System.out.println("[Thread B] Updated Record: Name: " + rsB.getString("name") +
                                ", New Name: " + rsB.getString("name"));
                    } else {
                        System.out.println("[Thread B] No record found with ID = 1 to update.");
                    }
                }
            } catch (SQLException | InterruptedException e) {
                System.err.println("Database error in Thread B: " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Start both threads
        threadA.start();
        threadB.start();
    }
}