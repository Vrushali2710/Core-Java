package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection()  {
        try {
            String jdbcUrl ="jdbc:h2:~/testdb";
            String username ="root";
            String password = "";
            this.connection = DriverManager.getConnection(jdbcUrl,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
    public static void closeConnection() {
        if(instance != null && instance.connection !=null){
            try{
                if(!instance.connection.isClosed()){
                    instance.connection.close();
                    System.out.println("Connection Closed");
                }

            } catch (Exception e){

            }
        }
    }
}
