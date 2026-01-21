package unienroll.service;

import unienroll.dao.RegistrationDao;
import unienroll.entity.Registration;

import java.util.List;

public class RegistrationService {

    private final RegistrationDao registrationDao;

    public RegistrationService(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
    public void register(Registration registration) {
        registrationDao.save(registration);
    }
    public List<Registration> getRegistrationsByStudent(int studentId) {
        return registrationDao.findByStudentId(studentId);
    }
    public List<Registration> getAllRegistrations() {
        return registrationDao.findAll();
    }
}

/* вроде должно работать */