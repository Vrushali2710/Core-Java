package org.example.model;

import jakarta.persistence.*;
import org.example.model.Student;

@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;

    @OneToOne(mappedBy = "laptop")
    private Student student;

    public Laptop() {}
    public Laptop(String model) { this.model = model; }

    public Long getId() { return id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}