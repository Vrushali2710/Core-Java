package org.example;

import java.sql.*;

public class JDBCExample{
    public static void main(String[] args)  {
        try{
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
            System.out.println("Connected");
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " + // <-- Added comma here
                    "name VARCHAR(50), " +                  // <-- Added comma here
                    "email VARCHAR(50))";
            stmt.execute(createTableSQL);
            System.out.println("users table created");

            String insertSQL = "INSERT INTO users(name,email) VALUES ('Alice','alice@example.com')";
            int rowsAffected = stmt.executeUpdate(insertSQL);
            System.out.println(rowsAffected);

            String selectSQL ="SELECT * FROM users";

            try (ResultSet rs = stmt.executeQuery(selectSQL)){
                while (rs.next()) {

                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    System.out.println("Name"+name +"Email"+email);
                }

            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        }
    }

