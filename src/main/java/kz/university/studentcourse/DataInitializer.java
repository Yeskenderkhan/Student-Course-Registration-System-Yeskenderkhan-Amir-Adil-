package kz.university.studentcourse;

import kz.university.studentcourse.entity.Course;
import kz.university.studentcourse.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(CourseRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // 1. BASICS (Без требований)
                Course javaBasic = repository.save(new Course("Java Basics", "Intro to Java syntax", 50, 3, "Computer Science", null));
                Course pythonBasic = repository.save(new Course("Python Start", "Variables and loops", 50, 3, "Computer Science", null));
                Course calc1 = repository.save(new Course("Calculus I", "Limits and Derivatives", 40, 4, "Mathematics", null));
                Course eng1 = repository.save(new Course("English A1", "Basic grammar", 30, 3, "Languages", null));

                // 2. INTERMEDIATE (Требуют Basic курсы)
                // Требует Java Basics
                repository.save(new Course("Java OOP", "Classes and Objects", 20, 4, "Computer Science", javaBasic));
                // Требует Python Start
                repository.save(new Course("Python Data Science", "Pandas & Numpy", 15, 4, "Computer Science", pythonBasic));
                // Требует Calculus I
                repository.save(new Course("Calculus II", "Integrals", 20, 4, "Mathematics", calc1));

                // 3. ADVANCED (Сложные и дорогие)
                repository.save(new Course("Spring Boot Pro", "Enterprise Web Apps", 10, 4, "Computer Science", javaBasic));
                repository.save(new Course("Machine Learning", "Neural Networks", 10, 4, "Computer Science", pythonBasic));
                repository.save(new Course("History of Kaz", "Main events", 60, 3, "Humanities", null));
                repository.save(new Course("Philosophy", "Critical thinking", 60, 3, "Humanities", null));
            }
        };
    }
}