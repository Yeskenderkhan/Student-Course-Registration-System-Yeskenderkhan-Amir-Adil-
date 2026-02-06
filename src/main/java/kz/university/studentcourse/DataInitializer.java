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
                // ==========================================
                // 1 YEAR (FRESHMAN) - NO PREREQUISITES
                // ==========================================

                // Math & Science
                Course calc1 = repository.save(new Course("Calculus I", "Limits, Derivatives, Integrals", 60, 5, "Mathematics", "Prof. Arman Suleimenov", null));
                Course physics1 = repository.save(new Course("Physics I", "Classical Mechanics", 50, 5, "Science", "Dr. Serik Bolatov", null));
                Course chem = repository.save(new Course("General Chemistry", "Molecules and Atoms", 40, 4, "Science", "Elena Ivanova", null));

                // CS Basics
                Course csInt = repository.save(new Course("Intro to CS", "Binary, Logic, History", 100, 3, "Computer Science", "Alan Turing", null));
                Course java1 = repository.save(new Course("Java Programming I", "Variables, Loops, Methods", 80, 4, "Computer Science", "Dr. Sarah Connor", null));
                Course py1 = repository.save(new Course("Python for Beginners", "Scripting basics", 80, 4, "Computer Science", "Assel Nurgalieva", null));

                // Humanities & Languages
                Course eng1 = repository.save(new Course("Academic English", "Writing & Speaking", 40, 3, "Languages", "John Smith", null));
                Course hist = repository.save(new Course("History of Kazakhstan", "From Khanate to Republic", 100, 3, "Humanities", "Prof. Kairat Akhmetov", null));
                Course phil = repository.save(new Course("Philosophy", "Ethics and Logic", 60, 3, "Humanities", "Dr. Aliya Maratova", null));
                Course econ1 = repository.save(new Course("Microeconomics", "Supply and Demand", 50, 3, "Economics", "Timur Batyrov", null));

                // ==========================================
                // 2 YEAR (SOPHOMORE) - REQUIRES YEAR 1
                // ==========================================

                // Math Path (All require Calculus I)
                Course calc2 = repository.save(new Course("Calculus II", "Multivariable Calculus", 40, 5, "Mathematics", "Prof. Arman Suleimenov", calc1));
                Course linAlg = repository.save(new Course("Linear Algebra", "Vectors & Matrices", 45, 4, "Mathematics", "Dr. Berik Serikov", calc1));
                Course stats = repository.save(new Course("Probability & Statistics", "Data Analysis", 50, 4, "Mathematics", "Olga Petrova", calc1));

                // Physics Path
                repository.save(new Course("Physics II", "Electromagnetism", 40, 5, "Science", "Dr. Serik Bolatov", physics1));

                // CS Path (Java)
                Course java2 = repository.save(new Course("Java OOP", "Polymorphism & Inheritance", 60, 5, "Computer Science", "Dr. Sarah Connor", java1));
                Course algo = repository.save(new Course("Algorithms & Data Structs", "Sorting, Trees, Graphs", 50, 5, "Computer Science", "Bekzat Tolegen", java1));
                Course db = repository.save(new Course("Databases (SQL)", "PostgreSQL & Design", 60, 4, "Computer Science", "Daulet Kudaibergen", java1));

                // CS Path (Python)
                Course ds = repository.save(new Course("Data Science Intro", "Pandas & Visualization", 40, 4, "Computer Science", "Assel Nurgalieva", py1));

                // Other
                Course eng2 = repository.save(new Course("Technical Writing", "Documentation & Reports", 30, 3, "Languages", "Emily Blunt", eng1));
                Course econ2 = repository.save(new Course("Macroeconomics", "Global Markets", 40, 3, "Economics", "Timur Batyrov", econ1));

                // ==========================================
                // 3 YEAR (JUNIOR) - HARD CORE
                // ==========================================

                // Requires Java OOP
                Course spring = repository.save(new Course("Spring Boot Framework", "Enterprise Web Apps", 30, 6, "Computer Science", "Yeskender Khan", java2));
                Course android = repository.save(new Course("Android Development", "Mobile Apps with Kotlin", 30, 5, "Computer Science", "Google Team", java2));

                // Requires Algorithms
                repository.save(new Course("Artificial Intelligence", "Search, Logic, Minimax", 25, 6, "Computer Science", "Dr. Geoffrey Hinton", algo));
                repository.save(new Course("Cyber Security", "Ethical Hacking", 25, 5, "Computer Science", "Mr. Robot", algo));

                // Requires Linear Algebra
                repository.save(new Course("Computer Graphics", "3D Rendering & OpenGL", 20, 5, "Computer Science", "John Carmack", linAlg));

                // Requires Data Science
                repository.save(new Course("Machine Learning", "Neural Networks", 20, 6, "Computer Science", "Andrew Ng", ds));

                // ==========================================
                // 4 YEAR (SENIOR) - FINAL
                // ==========================================

                // Requires Spring Boot
                repository.save(new Course("Microservices Arch", "Docker, K8s, Cloud", 15, 6, "Computer Science", "Martin Fowler", spring));

                // Requires Statistics
                repository.save(new Course("Big Data Analytics", "Hadoop & Spark", 20, 6, "Computer Science", "Matei Zaharia", stats));
            }
        };
    }
}