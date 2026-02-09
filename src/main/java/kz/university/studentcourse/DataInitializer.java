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

                // === 1. MATHEMATICS ===
                Course calc1 = repository.save(new Course("Calculus I", "Limits & Derivatives", 60, 5, "Mathematics", "Prof. Arman", null, "Mon/Wed 09:00", "Lec: 30h / Prac: 15h"));
                Course calc2 = repository.save(new Course("Calculus II", "Integrals", 40, 5, "Mathematics", "Prof. Arman", calc1, "Tue/Thu 09:00", "Lec: 30h / Prac: 15h"));
                Course linAlg = repository.save(new Course("Linear Algebra", "Matrices", 40, 4, "Mathematics", "Dr. Berik", calc1, "Fri 10:00", "Lec: 20h / Prac: 20h"));
                Course stats = repository.save(new Course("Probability & Stats", "Data Analysis", 50, 4, "Mathematics", "Olga P.", calc1, "Mon/Wed 14:00", "Lec: 25h / Lab: 15h"));
                Course diffEq = repository.save(new Course("Diff Equations", "Advanced Math", 30, 4, "Mathematics", "Dr. Sadykov", calc2, "Tue/Thu 16:00", "Lec: 30h / Prac: 10h"));

                // === 2. COMPUTER SCIENCE ===
                Course cs1 = repository.save(new Course("Intro to CS", "Binary & Logic", 100, 3, "Computer Science", "Alan Turing", null, "Mon 11:00", "Lec: 15h / Lab: 30h"));

                // Java Track (Imran Khaider)
                Course java1 = repository.save(new Course("Java Basics", "Variables & Loops", 80, 4, "Computer Science", "Imran Khaider", null, "Tue/Thu 10:00", "Lec: 20h / Code: 40h"));
                Course java2 = repository.save(new Course("Java OOP", "Classes & Objects", 60, 5, "Computer Science", "Imran Khaider", java1, "Mon/Wed 12:00", "Lec: 20h / Code: 40h"));
                Course spring = repository.save(new Course("Spring Boot", "Web Framework", 30, 6, "Computer Science", "Imran Khaider", java2, "Fri 15:00", "Lec: 15h / Proj: 50h"));
                Course android = repository.save(new Course("Android Dev", "Kotlin Apps", 30, 5, "Computer Science", "Imran Khaider", java2, "Tue/Thu 14:00", "Lec: 20h / Proj: 40h"));

                Course py1 = repository.save(new Course("Python Start", "Scripting", 80, 4, "Computer Science", "Assel N.", null, "Wed 10:00", "Lec: 15h / Lab: 30h"));
                Course algo = repository.save(new Course("Algorithms", "Trees & Graphs", 50, 5, "Computer Science", "Bekzat T.", java1, "Mon/Wed 16:00", "Lec: 30h / Prac: 20h"));
                Course ds = repository.save(new Course("Data Science", "Pandas & AI", 40, 4, "Computer Science", "Assel N.", py1, "Tue 12:00", "Lec: 20h / Lab: 25h"));
                Course db = repository.save(new Course("Databases", "SQL & Postgres", 60, 4, "Computer Science", "Daulet K.", java1, "Thu 11:00", "Lec: 20h / Lab: 20h"));
                Course cyber = repository.save(new Course("Cyber Security", "Ethical Hacking", 30, 5, "Computer Science", "Mr. Robot", cs1, "Fri 18:00", "Lec: 15h / Hack: 35h"));

                // === 3. MEDICINE ===
                Course bio = repository.save(new Course("Biology 101", "Cells & DNA", 60, 4, "Medicine", "Dr. House", null, "Mon/Wed 08:00", "Lec: 40h / Lab: 10h"));
                Course anat = repository.save(new Course("Anatomy", "Organs & Bones", 40, 5, "Medicine", "Dr. Strange", bio, "Tue/Thu 13:00", "Lec: 20h / Dissection: 40h"));
                Course chem = repository.save(new Course("Chemistry", "Periodic Table", 50, 4, "Medicine", "Walter White", null, "Fri 09:00", "Lec: 25h / Lab: 25h"));
                Course pharma = repository.save(new Course("Pharmacology", "Drugs & Effects", 30, 5, "Medicine", "Dr. Dre", chem, "Mon 15:00", "Lec: 30h / Lab: 20h"));
                Course surg = repository.save(new Course("Surgery Basics", "Stitches", 20, 6, "Medicine", "Dr. Grey", anat, "Wed 08:00", "Lec: 10h / Hosp: 60h"));

                // === 4. ECONOMICS & BUSINESS ===
                Course micro = repository.save(new Course("Microeconomics", "Supply & Demand", 60, 3, "Economics", "Timur B.", null, "Mon/Wed 11:00", "Lec: 30h / Sem: 15h"));
                Course macro = repository.save(new Course("Macroeconomics", "Global Economy", 50, 3, "Economics", "Elena I.", micro, "Tue/Thu 11:00", "Lec: 30h / Sem: 15h"));
                Course acc = repository.save(new Course("Accounting", "Balance Sheets", 50, 4, "Economics", "Scrooge McDuck", null, "Fri 14:00", "Lec: 20h / Prac: 30h"));
                Course mark = repository.save(new Course("Marketing", "Digital Ads", 50, 3, "Economics", "Steve Jobs", null, "Wed 13:00", "Lec: 25h / Proj: 15h"));
                Course fin = repository.save(new Course("Corporate Finance", "Stocks", 40, 4, "Economics", "Warren Buffett", acc, "Thu 09:00", "Lec: 30h / Prac: 20h"));

                // === 5. HUMANITIES & LAW ===
                Course hist = repository.save(new Course("History of KZ", "Ancient Times", 100, 3, "Humanities", "Prof. Akhmetov", null, "Mon 17:00", "Lec: 45h"));
                Course law = repository.save(new Course("Intro to Law", "Constitution", 70, 3, "Law", "Saul Goodman", null, "Tue 18:00", "Lec: 30h / Case: 15h"));
                Course civLaw = repository.save(new Course("Civil Law", "Contracts", 50, 4, "Law", "Harvey Specter", law, "Wed 18:00", "Lec: 30h / Court: 20h"));
                Course crimLaw = repository.save(new Course("Criminal Law", "Crime", 50, 4, "Law", "Matt Murdock", law, "Thu 19:00", "Lec: 30h / Court: 20h"));
                Course eng = repository.save(new Course("Academic English", "Writing", 50, 3, "Languages", "Shakespeare", null, "Mon/Wed 10:00", "Sem: 45h"));
            }
        };
    }
}