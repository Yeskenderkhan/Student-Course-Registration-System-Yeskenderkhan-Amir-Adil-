package kz.university.studentcourse.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();
    public Student() {}
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
    // Подсчет того сколько каждый курс сколько кредитов берет
    public int getCurrentCredits() {
        return courses.stream().mapToInt(Course::getCredits).sum();
    }
    // опять геттеры и сеттеры добавленин
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Course> getCourses() { return courses; }
}
// все норм