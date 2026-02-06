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
                repository.save(new Course("Java Programming", "Learn Java from scratch", 5)); // Всего 5 мест
                repository.save(new Course("Python for AI", "Machine Learning basics", 2));    // Всего 2 места
                repository.save(new Course("Web Design", "HTML & CSS masterclass", 10));
            }
        };
    }
}