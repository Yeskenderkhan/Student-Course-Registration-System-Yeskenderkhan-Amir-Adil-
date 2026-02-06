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
                // === MATH & CS (Base) ===
                Course calc1 = repository.save(new Course("Calculus I", "Limits & Derivatives", 60, 5, "Mathematics", "Prof. Arman Suleimenov", null));
                Course cs1 = repository.save(new Course("Intro to CS", "Binary & Logic", 100, 3, "Computer Science", "Alan Turing", null));
                Course java1 = repository.save(new Course("Java Basics", "Variables & Loops", 80, 4, "Computer Science", "Dr. Sarah Connor", null));
                Course py1 = repository.save(new Course("Python Start", "Scripting", 80, 4, "Computer Science", "Assel Nurgalieva", null));

                // === MATH (Advanced) - Requires Calc 1 ===
                Course calc2 = repository.save(new Course("Calculus II", "Integrals", 40, 5, "Mathematics", "Prof. Arman Suleimenov", calc1));
                Course linAlg = repository.save(new Course("Linear Algebra", "Matrices", 40, 4, "Mathematics", "Dr. Berik Serikov", calc1));
                Course stats = repository.save(new Course("Statistics", "Probability", 50, 4, "Mathematics", "Olga Petrova", calc1));

                // === CS (Advanced) ===
                Course java2 = repository.save(new Course("Java OOP", "Classes & Objects", 60, 5, "Computer Science", "Dr. Sarah Connor", java1));
                Course algo = repository.save(new Course("Algorithms", "Trees & Graphs", 50, 5, "Computer Science", "Bekzat Tolegen", java1));
                Course ds = repository.save(new Course("Data Science", "Pandas & AI", 40, 4, "Computer Science", "Assel Nurgalieva", py1));
                Course spring = repository.save(new Course("Spring Boot", "Web Framework", 30, 6, "Computer Science", "Yeskender Khan", java2));
                Course android = repository.save(new Course("Android Dev", "Kotlin Apps", 30, 5, "Computer Science", "Google Team", java2));
                Course ai = repository.save(new Course("AI & ML", "Neural Networks", 25, 6, "Computer Science", "Andrew Ng", ds));
                Course cyber = repository.save(new Course("Cyber Security", "Ethical Hacking", 30, 5, "Computer Science", "Mr. Robot", cs1));

                // === MEDICINE (New!) ===
                Course bio = repository.save(new Course("General Biology", "Cells & DNA", 60, 4, "Medicine", "Dr. House", null));
                Course anat = repository.save(new Course("Human Anatomy", "Organs & Bones", 40, 5, "Medicine", "Dr. Strange", bio));
                Course genetics = repository.save(new Course("Genetics", "CRISPR & Genes", 30, 5, "Medicine", "Dr. Watson", bio));
                Course surgery = repository.save(new Course("Intro to Surgery", "Basic stitches", 20, 6, "Medicine", "Dr. Grey", anat));

                // === LAW (New!) ===
                Course law1 = repository.save(new Course("Intro to Law", "Constitution", 70, 3, "Law", "Saul Goodman", null));
                Course civil = repository.save(new Course("Civil Law", "Contracts", 50, 4, "Law", "Harvey Specter", law1));
                Course crim = repository.save(new Course("Criminal Law", "Crime & Punishment", 50, 4, "Law", "Matt Murdock", law1));
                Course intLaw = repository.save(new Course("International Law", "UN & Treaties", 40, 4, "Law", "Amal Clooney", law1));

                // === ECONOMICS ===
                Course micro = repository.save(new Course("Microeconomics", "Supply & Demand", 60, 3, "Economics", "Timur Batyrov", null));
                Course macro = repository.save(new Course("Macroeconomics", "Global Economy", 50, 3, "Economics", "Elena Ivanova", micro));
                Course finance = repository.save(new Course("Corporate Finance", "Stocks & Bonds", 40, 4, "Economics", "Warren Buffett", micro));
                Course market = repository.save(new Course("Marketing", "Digital Ads", 50, 3, "Economics", "Steve Jobs", null));

                // === ARTS & HUMANITIES (New!) ===
                Course hist = repository.save(new Course("History of KZ", "Ancient Times", 100, 3, "Humanities", "Prof. Akhmetov", null));
                Course phil = repository.save(new Course("Philosophy", "Logic", 80, 3, "Humanities", "Dr. Watts", null));
                Course psych = repository.save(new Course("Psychology", "The Mind", 70, 3, "Humanities", "Sigmund Freud", null));
                Course art = repository.save(new Course("Art History", "Renaissance", 40, 3, "Arts", "Leonardo da Vinci", null));
                Course music = repository.save(new Course("Music Theory", "Notes & Rhythm", 30, 3, "Arts", "Mozart", null));

                // === SPORTS (New!) ===
                repository.save(new Course("Physical Ed", "Gym & Fitness", 100, 2, "Sports", "Coach Carter", null));
                repository.save(new Course("Football", "Team Strategy", 40, 2, "Sports", "Lionel Messi", null));
                repository.save(new Course("Chess", "Strategy", 30, 2, "Sports", "Magnus Carlsen", null));
            }
        };
    }
}