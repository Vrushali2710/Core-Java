package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsertExample {
    public static void insertUsersInBatch() {
        String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";
        Connection con = null;

        try {
            // Get the shared connection
            con = DatabaseConnection.getInstance().getConnection();

            // Try-with-resources ONLY for the statement
            try (PreparedStatement pstmt = con.prepareStatement(insertSql)) {
                con.setAutoCommit(false); // Important for batching

                for (int i = 1; i <= 1000; i++) {
                    pstmt.setString(1, "User" + i);
                    pstmt.setString(2, "User" + i + "@gmail.com");
                    pstmt.addBatch();
                }

                pstmt.executeBatch();
                con.commit(); // Commit the transaction
                System.out.println("Batch successfully executed!");
            }
        } catch (SQLException e) {
            System.err.println("Database error during batch insertion!");
            e.printStackTrace();
            // Rollback if something goes wrong
            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            // Re-enable auto-commit so subsequent select queries work normally
            try { if (con != null) con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}