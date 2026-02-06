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
                // === 1. FUNDAMENTAL / CORE (Нет требований) ===
                Course calc1 = repository.save(new Course("Calculus I", "Limits & Derivatives", 50, 4, "Mathematics", "Prof. Arman Suleimenov", null));
                Course java1 = repository.save(new Course("Intro to Java", "Basic Syntax & Loops", 60, 3, "Computer Science", "Dr. Sarah Connor", null));
                Course py1 = repository.save(new Course("Python Basics", "Variables & Functions", 60, 3, "Computer Science", "Assel Nurgalieva", null));
                Course eng1 = repository.save(new Course("English A1", "Elementary Level", 30, 3, "Languages", "John Smith", null));
                Course hist = repository.save(new Course("History of Kazakhstan", "Ancient to Modern", 100, 3, "Humanities", "Prof. Kairat Akhmetov", null));
                Course phil = repository.save(new Course("Philosophy", "Critical Thinking", 80, 3, "Humanities", "Dr. Alan Watts", null));
                Course physics = repository.save(new Course("Physics I", "Mechanics", 40, 4, "Science", "Dr. Serik Bolatov", null));
                Course macro = repository.save(new Course("Macroeconomics", "Global Economy", 50, 3, "Economics", "Elena Ivanova", null));

                // === 2. INTERMEDIATE (Требуют 1-й уровень) ===

                // MATH PATH
                Course calc2 = repository.save(new Course("Calculus II", "Integrals & Series", 40, 4, "Mathematics", "Prof. Arman Suleimenov", calc1));
                Course linAlg = repository.save(new Course("Linear Algebra", "Matrices & Vectors", 40, 4, "Mathematics", "Dr. Berik Serikov", calc1));

                // CS PATH (Java)
                Course javaOOP = repository.save(new Course("Java OOP", "Classes, Objects, Inheritance", 30, 4, "Computer Science", "Dr. Sarah Connor", java1));
                Course algorithms = repository.save(new Course("Algorithms & Data Structures", "Sorting, Trees, Graphs", 30, 4, "Computer Science", "Timur Batyrov", java1));

                // CS PATH (Python)
                Course dataScience = repository.save(new Course("Data Science Intro", "Pandas, Matplotlib", 25, 3, "Computer Science", "Assel Nurgalieva", py1));

                // OTHER
                Course eng2 = repository.save(new Course("Academic Writing", "Essay & Research", 25, 3, "Languages", "Emily Blunt", eng1));
                Course physics2 = repository.save(new Course("Physics II", "Electromagnetism", 30, 4, "Science", "Dr. Serik Bolatov", physics));

                // === 3. ADVANCED (Требуют 2-й уровень) ===

                // Requires Calculus II
                repository.save(new Course("Differential Equations", "Complex Math", 20, 4, "Mathematics", "Prof. Olga Petrova", calc2));

                // Requires Java OOP
                Course spring = repository.save(new Course("Spring Boot Framework", "Web Apps & Microservices", 20, 4, "Computer Science", "Bekzat Tolegen", javaOOP));
                repository.save(new Course("Android Development", "Mobile Apps with Java/Kotlin", 20, 4, "Computer Science", "Daulet Kudaibergen", javaOOP));

                // Requires Data Science
                repository.save(new Course("Machine Learning", "Neural Networks & AI", 15, 4, "Computer Science", "Dr. Geoffrey Hinton", dataScience));

                // Requires Algorithms
                repository.save(new Course("Competitive Programming", "Advanced Algorithms", 15, 3, "Computer Science", "Timur Batyrov", algorithms));

                // Requires Linear Algebra
                repository.save(new Course("Computer Graphics", "3D Rendering Math", 20, 3, "Computer Science", "Alexey Smirnov", linAlg));
            }
        };
    }
}