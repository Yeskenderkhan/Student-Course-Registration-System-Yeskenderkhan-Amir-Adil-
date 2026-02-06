package kz.university.studentcourse.repository;

import kz.university.studentcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Этот метод нужен для нашей новой системы логина по Email
    Student findByEmail(String email);
}