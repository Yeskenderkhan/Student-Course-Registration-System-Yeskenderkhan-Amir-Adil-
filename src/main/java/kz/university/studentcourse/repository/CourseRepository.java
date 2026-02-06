package kz.university.studentcourse.repository;

import kz.university.studentcourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // SQL поиск: ищем совпадение в названии ИЛИ в имени преподавателя (без учета регистра)
    @Query("SELECT c FROM Course c WHERE lower(c.title) LIKE lower(concat('%', :keyword, '%')) OR lower(c.instructor) LIKE lower(concat('%', :keyword, '%'))")
    List<Course> searchCourses(String keyword);
}