package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchInsertExample {
    public void insertUsersInBatch() {
        String insertSql = "INSERT INTO users (name,email) VALUES(?,?)";
        Connection con = DatabaseConnection.getInstance().getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(insertSql);
            con.setAutoCommit(false);
            for(int i=1;i<=1000;i++) {
                pstmt.setString(1,"User"+i);


            }

        } catch (Exception e){

        }
    }
}
