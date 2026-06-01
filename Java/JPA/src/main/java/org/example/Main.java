package org.example;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService service = new UserService();

        // ── CREATE ──────────────────────────────
        System.out.println("=== CREATE ===");
        service.createUser("Alice", "alice@example.com");
        service.createUser("Bob",   "bob@example.com");
        service.createUser("Charlie", "charlie@example.com");

        // ── READ ALL ────────────────────────────
        System.out.println("\n=== GET ALL USERS ===");
        List<User> users = service.getAllUsers();
        users.forEach(System.out::println);

        // ── READ BY ID ──────────────────────────
        System.out.println("\n=== GET USER BY ID ===");
        User user = service.getUserById(1L);
        System.out.println("Found: " + user);

        // ── READ BY EMAIL ───────────────────────
        System.out.println("\n=== GET USER BY EMAIL ===");
        User byEmail = service.getUserByEmail("bob@example.com");
        System.out.println("Found: " + byEmail);

        // ── UPDATE ──────────────────────────────
        System.out.println("\n=== UPDATE ===");
        service.updateUser(1L, "Alice Smith", "alicesmith@example.com");

        // ── DELETE ──────────────────────────────
        System.out.println("\n=== DELETE ===");
        service.deleteUser(3L);

        // ── FINAL LIST ──────────────────────────
        System.out.println("\n=== FINAL LIST ===");
        service.getAllUsers().forEach(System.out::println);

        // ── SHUTDOWN ────────────────────────────
        JPAUtil.shutdown();
    }
}