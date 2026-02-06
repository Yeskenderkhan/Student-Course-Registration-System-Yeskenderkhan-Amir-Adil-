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

    private int capacity; // Максимальный лимит (например, 15)

    // Связь ManyToMany, чтобы мы знали, кто записан на курс
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Course(String title, String description, int capacity) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }

    // Логика для шаблона: сколько мест занято?
    public int getEnrolledCount() {
        return students.size();
    }

    // Логика для шаблона: остались ли места?
    public boolean hasSeats() {
        return students.size() < capacity;
    }

    // Логика: набран ли минимум для старта (например, 60% мест)
    public boolean isSuccess() {
        return students.size() >= (capacity * 0.6);
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public Set<Student> getStudents() { return students; }
}