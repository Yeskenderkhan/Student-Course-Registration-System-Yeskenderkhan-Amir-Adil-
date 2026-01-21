package unienroll.service;

import unienroll.dao.CourseDao;
import unienroll.entity.Course;

import java.util.List;

public class CourseService {

    private final CourseDao courseDao;

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    public void addCourse(Course course) {
        courseDao.save(course);
    }
    public Course getCourseById(int id) {
        return courseDao.findById(id);
    }
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }
}