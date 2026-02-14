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

                // === 1. MATHEMATICS (Prac) ===
                Course calc1 = repository.save(new Course("Calculus I", "Limits & Derivatives", 60, 5, "Mathematics", "Prof. Arman", null,
                        "Mon/Wed 09:00-10:15", "Lec: 30h / Prac: 15h"));

                Course calc2 = repository.save(new Course("Calculus II", "Integrals", 40, 5, "Mathematics", "Prof. Arman", calc1,
                        "Tue/Thu 09:00-10:15", "Lec: 30h / Prac: 15h"));

                Course linAlg = repository.save(new Course("Linear Algebra", "Matrices", 40, 4, "Mathematics", "Dr. Berik", calc1,
                        "Mon/Wed 10:30-11:45", "Lec: 20h / Prac: 20h"));

                Course stats = repository.save(new Course("Probability & Stats", "Data Analysis", 50, 4, "Mathematics", "Olga P.", calc1,
                        "Fri 09:00-11:50", "Lec: 25h / Lab: 15h")); // Тут Lab, так как анализ данных

                Course diffEq = repository.save(new Course("Diff Equations", "Advanced Math", 30, 4, "Mathematics", "Dr. Sadykov", calc2,
                        "Tue/Thu 10:30-11:45", "Lec: 30h / Prac: 15h"));

                Course discrete = repository.save(new Course("Discrete Math", "Logic & Sets", 50, 4, "Mathematics", "Dr. Logic", null,
                        "Mon/Wed 13:00-14:15", "Lec: 20h / Prac: 20h"));

                // === 2. COMPUTER SCIENCE (Lab) ===
                Course cs1 = repository.save(new Course("Intro to CS", "Binary & Logic", 100, 3, "Computer Science", "Alan Turing", null,
                        "Mon 14:30-16:20", "Lec: 15h / Lab: 30h"));

                Course java1 = repository.save(new Course("Java Basics", "Variables & Loops", 80, 4, "Computer Science", "Imran Khaider", null,
                        "Tue/Thu 13:00-14:15", "Lec: 20h / Lab: 40h"));

                Course java2 = repository.save(new Course("Java OOP", "Classes & Objects", 60, 5, "Computer Science", "Imran Khaider", java1,
                        "Mon/Wed 14:30-15:45", "Lec: 20h / Lab: 40h"));

                Course spring = repository.save(new Course("Spring Boot", "Web Framework", 30, 6, "Computer Science", "Imran Khaider", java2,
                        "Fri 14:00-16:50", "Lec: 15h / Lab: 60h"));

                Course android = repository.save(new Course("Android Dev", "Kotlin Apps", 30, 5, "Computer Science", "Imran Khaider", java2,
                        "Tue/Thu 14:30-15:45", "Lec: 20h / Lab: 40h"));

                Course py1 = repository.save(new Course("Python Start", "Scripting", 80, 4, "Computer Science", "Assel N.", null,
                        "Mon/Wed 16:00-17:15", "Lec: 15h / Lab: 30h"));

                Course algo = repository.save(new Course("Algorithms", "Trees & Graphs", 50, 5, "Computer Science", "Bekzat T.", java1,
                        "Tue/Thu 16:00-17:15", "Lec: 30h / Lab: 30h"));

                Course ds = repository.save(new Course("Data Science", "Pandas & AI", 40, 4, "Computer Science", "Assel N.", py1,
                        "Fri 10:00-12:50", "Lec: 20h / Lab: 40h"));

                Course db = repository.save(new Course("Databases", "SQL & Postgres", 60, 4, "Computer Science", "Daulet K.", java1,
                        "Mon/Wed 17:30-18:45", "Lec: 20h / Lab: 20h"));

                Course cyber = repository.save(new Course("Cyber Security", "Ethical Hacking", 30, 5, "Computer Science", "Mr. Robot", cs1,
                        "Fri 17:00-19:50", "Lec: 15h / Lab: 45h"));

                Course gameDev = repository.save(new Course("Game Dev", "Unity & C#", 40, 5, "Computer Science", "Kojima", cs1,
                        "Thu 18:00-20:50", "Lec: 20h / Lab: 40h"));

                // === 3. SCIENCE (Lab) ===
                Course phys1 = repository.save(new Course("Physics I", "Mechanics", 60, 4, "Science", "Newton", null,
                        "Tue/Thu 09:00-10:15", "Lec: 30h / Lab: 15h"));

                Course phys2 = repository.save(new Course("Physics II", "Electricity", 50, 4, "Science", "Tesla", phys1,
                        "Mon/Wed 09:00-10:15", "Lec: 30h / Lab: 15h"));

                Course chem1 = repository.save(new Course("Chemistry I", "Atoms", 60, 4, "Science", "Marie Curie", null,
                        "Mon/Wed 10:30-11:45", "Lec: 30h / Lab: 20h"));

                Course bio = repository.save(new Course("Biology", "Cells", 70, 3, "Science", "Darwin", null,
                        "Fri 13:00-14:15", "Lec: 20h / Lab: 20h"));

                // === 4. ECONOMICS (Prac) ===
                Course micro = repository.save(new Course("Microeconomics", "Supply & Demand", 60, 3, "Economics", "Timur B.", null,
                        "Tue/Thu 13:00-14:15", "Lec: 30h / Prac: 15h"));

                Course macro = repository.save(new Course("Macroeconomics", "Global Economy", 50, 3, "Economics", "Elena I.", micro,
                        "Mon/Wed 13:00-14:15", "Lec: 30h / Prac: 15h"));

                Course acc = repository.save(new Course("Accounting", "Balance Sheets", 50, 4, "Economics", "Scrooge", null,
                        "Tue/Thu 14:30-15:45", "Lec: 20h / Prac: 30h"));

                Course mark = repository.save(new Course("Marketing", "Digital Ads", 50, 3, "Economics", "Jobs", null,
                        "Fri 15:00-17:50", "Lec: 25h / Prac: 20h"));

                // === 5. HUMANITIES (Prac) ===
                Course hist = repository.save(new Course("History of KZ", "Ancient Times", 100, 3, "Humanities", "Prof. Akhmetov", null,
                        "Mon 18:00-20:50", "Lec: 45h / Prac: 15h"));

                Course phil = repository.save(new Course("Philosophy", "Logic", 80, 3, "Humanities", "Aristotle", null,
                        "Wed 18:00-20:50", "Lec: 30h / Prac: 15h"));

                Course eng = repository.save(new Course("Academic English", "Writing", 50, 3, "Languages", "Shakespeare", null,
                        "Mon/Wed 08:00-08:50", "Lec: 0h / Prac: 45h"));

                Course psych = repository.save(new Course("Psychology", "Human Mind", 60, 3, "Humanities", "Freud", null,
                        "Fri 10:00-11:15", "Lec: 20h / Prac: 10h"));

                Course art = repository.save(new Course("Art History", "Renaissance", 40, 3, "Humanities", "Da Vinci", null,
                        "Thu 14:00-16:50", "Lec: 30h / Prac: 15h"));

                // === CONFLICT DEMO (Prac) ===
                Course soc = repository.save(new Course("Sociology", "Society", 60, 3, "Humanities", "Weber", null,
                        "Tue/Thu 11:00-12:15", "Lec: 20h / Prac: 10h"));

                Course poli = repository.save(new Course("Political Sci", "Gov Systems", 50, 3, "Humanities", "Machiavelli", null,
                        "Tue/Thu 11:30-12:45", "Lec: 20h / Prac: 10h"));
            }
        };
    }
}