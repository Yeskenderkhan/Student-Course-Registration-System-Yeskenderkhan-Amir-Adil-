package kz.university.studentcourse.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int seats;      // Места
    private int credits;    // Стоимость (3-4)
    private String category; // Категория (Core, Math, etc.)

    // Пререквизит (какой курс ОБЯЗАТЕЛЕН перед этим)
    @OneToOne
    private Course prerequisite;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Course(String title, String description, int seats, int credits, String category, Course prerequisite) {
        this.title = title;
        this.description = description;
        this.seats = seats;
        this.credits = credits;
        this.category = category;
        this.prerequisite = prerequisite;
    }

    public boolean hasSeats() { return students.size() < seats; }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getSeats() { return seats; }
    public int getCredits() { return credits; }
    public String getCategory() { return category; }
    public Course getPrerequisite() { return prerequisite; }
    public Set<Student> getStudents() { return students; }
    public void setSeats(int seats) { this.seats = seats; }
}