package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public void fetchAllUsers() {
        String name = "Alice";
        String selectSQL = "SELECT * FROM users";

        try {
            Connection con = DatabaseConnection.getInstance().getConnection();

            // Try-with-resources manages the statement and result set, leaving con open
            try (PreparedStatement pstmt = con.prepareStatement(selectSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY)) {


                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String qName = rs.getString("name");
                        String qEmail = rs.getString("email");
                        System.out.println("Name: " + qName + " || Email: " + qEmail);
//                        rs.updateString(1,"john"); CONCUR_READ_ONLY it will throw exceptiom
                        rs.updateString(1,"john");

                    }
//                    System.out.println("\n--- Jumping to the Last Row ---");  //TYPE_FORWARD_ONLY
//                    if (rs.last()) {
//                        System.out.println("Last Row: " + rs.getString("name"));
//                        // Get the current row number (useful for counting total rows)
//                        System.out.println("Total Rows: " + rs.getRow());
//                    }
//
//                    System.out.println("\n--- Moving Backwards ---");
//                    if (rs.previous()) {
//                        System.out.println("Second-to-last Row: " + rs.getString("name"));
//                    }
//
//                    System.out.println("\n--- Jumping to an Absolute Position ---");
//                    // Jump directly to the 1st row (1-indexed)
//                    if (rs.absolute(1)) {
//                        System.out.println("Back to Row 1: " + rs.getString("name"));
//                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error while fetching users!");
            e.printStackTrace();
        }
    }
}