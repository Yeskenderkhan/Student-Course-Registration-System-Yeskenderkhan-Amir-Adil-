package unienroll.dao;

import unienroll.entity.Course;
import java.util.List;

public interface CourseDao {
    void save(Course course);
    Course findById(int id);
    List<Course> findAll();
}