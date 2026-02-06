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
            // Заполняем только если база пустая
            if (repository.count() == 0) {

                // === 1. MATHEMATICS ===
                Course calc1 = repository.save(new Course("Calculus I", "Limits & Derivatives", 60, 5, "Mathematics", "Prof. Arman", null));
                Course calc2 = repository.save(new Course("Calculus II", "Integrals", 40, 5, "Mathematics", "Prof. Arman", calc1));
                Course linAlg = repository.save(new Course("Linear Algebra", "Matrices", 40, 4, "Mathematics", "Dr. Berik", calc1));
                Course stats = repository.save(new Course("Probability & Stats", "Data Analysis", 50, 4, "Mathematics", "Olga P.", calc1));
                Course diffEq = repository.save(new Course("Diff Equations", "Advanced Math", 30, 4, "Mathematics", "Dr. Sadykov", calc2));

                // === 2. COMPUTER SCIENCE (Imran Khaider Edition) ===
                Course cs1 = repository.save(new Course("Intro to CS", "Binary & Logic", 100, 3, "Computer Science", "Alan Turing", null));

                // --- JAVA TRACK (Imran Khaider) ---
                Course java1 = repository.save(new Course("Java Basics", "Variables & Loops", 80, 4, "Computer Science", "Imran Khaider", null));
                Course java2 = repository.save(new Course("Java OOP", "Classes & Objects", 60, 5, "Computer Science", "Imran Khaider", java1));
                Course spring = repository.save(new Course("Spring Boot", "Web Framework", 30, 6, "Computer Science", "Imran Khaider", java2));
                Course android = repository.save(new Course("Android Dev", "Kotlin Apps", 30, 5, "Computer Science", "Imran Khaider", java2));
                // ----------------------------------

                Course py1 = repository.save(new Course("Python Start", "Scripting", 80, 4, "Computer Science", "Assel N.", null));
                Course algo = repository.save(new Course("Algorithms", "Trees & Graphs", 50, 5, "Computer Science", "Bekzat T.", java1));
                Course ds = repository.save(new Course("Data Science", "Pandas & AI", 40, 4, "Computer Science", "Assel N.", py1));
                Course db = repository.save(new Course("Databases", "SQL & Postgres", 60, 4, "Computer Science", "Daulet K.", java1));

                Course ml = repository.save(new Course("Machine Learning", "Neural Networks", 25, 6, "Computer Science", "Andrew Ng", ds));
                Course cyber = repository.save(new Course("Cyber Security", "Ethical Hacking", 30, 5, "Computer Science", "Mr. Robot", cs1));

                // === 3. MEDICINE ===
                Course bio = repository.save(new Course("Biology 101", "Cells & DNA", 60, 4, "Medicine", "Dr. House", null));
                Course anat = repository.save(new Course("Anatomy", "Organs & Bones", 40, 5, "Medicine", "Dr. Strange", bio));
                Course chem = repository.save(new Course("Chemistry", "Periodic Table", 50, 4, "Medicine", "Walter White", null));
                Course pharma = repository.save(new Course("Pharmacology", "Drugs & Effects", 30, 5, "Medicine", "Dr. Dre", chem));
                Course surg = repository.save(new Course("Surgery Basics", "Stitches", 20, 6, "Medicine", "Dr. Grey", anat));

                // === 4. ECONOMICS & BUSINESS ===
                Course micro = repository.save(new Course("Microeconomics", "Supply & Demand", 60, 3, "Economics", "Timur B.", null));
                Course macro = repository.save(new Course("Macroeconomics", "Global Economy", 50, 3, "Economics", "Elena I.", micro));
                Course acc = repository.save(new Course("Accounting", "Balance Sheets", 50, 4, "Economics", "Scrooge McDuck", null));
                Course mark = repository.save(new Course("Marketing", "Digital Ads", 50, 3, "Economics", "Steve Jobs", null));
                Course fin = repository.save(new Course("Corporate Finance", "Stocks", 40, 4, "Economics", "Warren Buffett", acc));
                Course man = repository.save(new Course("Management", "Leadership", 60, 3, "Economics", "Michael Scott", null));

                // === 5. HUMANITIES & LAW ===
                Course hist = repository.save(new Course("History of KZ", "Ancient Times", 100, 3, "Humanities", "Prof. Akhmetov", null));
                Course law = repository.save(new Course("Intro to Law", "Constitution", 70, 3, "Law", "Saul Goodman", null));
                Course civLaw = repository.save(new Course("Civil Law", "Contracts", 50, 4, "Law", "Harvey Specter", law));
                Course crimLaw = repository.save(new Course("Criminal Law", "Crime", 50, 4, "Law", "Matt Murdock", law));
                Course phil = repository.save(new Course("Philosophy", "Logic", 80, 3, "Humanities", "Aristotle", null));
                Course eng = repository.save(new Course("Academic English", "Writing", 50, 3, "Languages", "Shakespeare", null));
            }
        };
    }
}