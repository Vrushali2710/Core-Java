package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Database Operations ---");

        UserDAO dao = new UserDAO();

        System.out.println("Fetching users...");
        dao.fetchAllUsers(); // Uses shared connection (leaves it open)

        System.out.println("Starting batch insert...");
//        BatchInsertExample.insertUsersInBatch(); // Uses shared connection (leaves it open)

        System.out.println("Fetching users again to verify insert...");
        dao.fetchAllUsers(); // Works perfectly now!
        LiveScrollExample ls = new LiveScrollExample();
        ls.findData();

        // Explicitly close the single connection right before application exits
        DatabaseConnection.getInstance().closeConnection();
    }
}