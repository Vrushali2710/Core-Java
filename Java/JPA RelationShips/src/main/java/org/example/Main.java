package org.example;

import org.example.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Create the Factory using the persistence-unit name from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UniversityPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Begin Transaction
            tx.begin();

            // 1. Setup Department (One-to-Many side)
            Department csDept = new Department("Computer Science");
            em.persist(csDept);

            // 2. Setup Courses (Many-to-Many side)
            Course javaCourse = new Course("Advanced Java");
            Course dbCourse = new Course("Relational Databases");
            em.persist(javaCourse);
            em.persist(dbCourse);

            // 3. Setup Laptops (One-to-One side)
            Laptop laptop1 = new Laptop("Apple MacBook Pro");
            Laptop laptop2 = new Laptop("Dell XPS 15");

            // 4. Setup Students and build relationships
            Student student1 = new Student("Alice");
            student1.setDepartment(csDept);
            student1.setLaptop(laptop1); // One-to-One assignment
            student1.getCourses().add(javaCourse); // Many-to-Many assignment
            student1.getCourses().add(dbCourse);

            Student student2 = new Student("Bob");
            student2.setDepartment(csDept);
            student2.setLaptop(laptop2);
            student2.getCourses().add(javaCourse);

            // 5. Persist the Students
            // Because Student has CascadeType.ALL on Laptop, laptop1 and laptop2 are saved automatically.
            em.persist(student1);
            em.persist(student2);

            // Commit Transaction to push to H2 database
            tx.commit();
            System.out.println("\n>>> Data successfully saved to H2 Database! <<<\n");

            // 6. Verification: Read data back from DB in a new session clear
            em.clear();

            Student foundStudent = em.find(Student.class, 1L);
            System.out.println("--- Student Verification ---");
            System.out.println("Student Name: " + foundStudent.getName());
            System.out.println("Assigned Laptop: " + foundStudent.getLaptop().getModel());
            System.out.println("Department: " + foundStudent.getDepartment().getName());
            System.out.println("Enrolled Courses count: " + foundStudent.getCourses().size());
            Student foundStudent2 = em.find(Student.class,2L);
            System.out.println("------------student 2--------------");
            System.out.println("student name "+foundStudent2.getName());
            System.out.println("student courses"+foundStudent2.getCourses().stream().map(Course::getTitle).collect(Collectors.joining(",")));

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cleanup resources
            em.close();
            emf.close();
        }
    }
}