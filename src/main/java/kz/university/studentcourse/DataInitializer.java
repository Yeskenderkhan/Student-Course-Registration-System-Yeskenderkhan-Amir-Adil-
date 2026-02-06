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
                // Создаем курсы с разными лимитами
                repository.save(new Course("Java Enterprise", "Spring Boot & Microservices deep dive", 15));
                repository.save(new Course("Python for Data Science", "Pandas, NumPy, and AI basics", 12));
                repository.save(new Course("Web Design & UI/UX", "Figma, HTML, CSS, Bootstrap", 20));
                repository.save(new Course("Cyber Security", "Ethical Hacking basics", 10));
                repository.save(new Course("Mobile Dev (Kotlin)", "Android Development from scratch", 15));
                repository.save(new Course("Database Architecture", "SQL, PostgreSQL, NoSQL", 25));
            }
        };
    }
}