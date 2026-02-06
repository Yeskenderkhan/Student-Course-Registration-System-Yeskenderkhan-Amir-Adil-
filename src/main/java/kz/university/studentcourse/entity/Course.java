package kz.university.studentcourse.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int seats; // Всего мест

    public Course() {}

    public Course(String title, String description, int seats) {
        this.title = title;
        this.description = description;
        this.seats = seats;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
}