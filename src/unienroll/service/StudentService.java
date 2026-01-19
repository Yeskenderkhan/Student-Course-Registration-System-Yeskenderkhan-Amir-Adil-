package unienroll.service;

import unienroll.dao.StudentDao;
import unienroll.entity.Student;

public class StudentService {
    private final StudentDao studentDao;
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    public void registerStudent(Student student) {
        studentDao.save(student);
    }
}
