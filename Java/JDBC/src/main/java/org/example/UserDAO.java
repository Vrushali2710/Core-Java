package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Predicate;

public class UserDAO {
    public void fetchAllUsers() {
        String name = "Alice";
        String selectSQL = "SELECT * FROM users WHERE name = ?";
        try {
            Connection con = DatabaseConnection.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String qName = rs.getString("name");
                String qEmail = rs.getString("email");
                System.out.println("Name: "+qName+"|| Email: "+qEmail);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
