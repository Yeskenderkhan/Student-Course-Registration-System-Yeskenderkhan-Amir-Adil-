package unienroll.dao;

import unienroll.entity.Registration;
import java.util.List;

public interface RegistrationDao {

    void save(Registration registration);

    List<Registration> findByStudentId(int studentId);
    List<Registration> findAll();
}