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
    private int seats;
    private int credits;
    private String category;
    private String instructor;

    private String schedule;  // Пример: "Mon, Wed 10:00"
    private String workload;  // Пример: "Lec: 20h / Lab: 10h"
    private String duration;  // Пример: "15 Weeks"

    @ManyToOne
    @JoinColumn(name = "prerequisite_id")
    private Course prerequisite;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Course() {}

    // Обновленный конструктор
    public Course(String title, String description, int seats, int credits, String category, String instructor,
                  Course prerequisite, String schedule, String workload) {
        this.title = title;
        this.description = description;
        this.seats = seats;
        this.credits = credits;
        this.category = category;
        this.instructor = instructor;
        this.prerequisite = prerequisite;
        this.schedule = schedule;
        this.workload = workload;
        this.duration = "15 Weeks"; // Стандарт для всех
    }

    public boolean hasSeats() { return students.size() < seats; }
    public int getEnrolledCount() { return students.size(); }

    // Геттеры
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
    public int getCredits() { return credits; }
    public String getCategory() { return category; }
    public String getInstructor() { return instructor; }
    public Course getPrerequisite() { return prerequisite; }
    public Set<Student> getStudents() { return students; }

    // Новые геттеры
    public String getSchedule() { return schedule; }
    public String getWorkload() { return workload; }
    public String getDuration() { return duration; }
}