package unienroll.dao;

import unienroll.entity.Student;
import java.util.List;

public interface StudentDao {
    void save(Student student);
    Student findById(int id);
    List<Student> findAll();
}
