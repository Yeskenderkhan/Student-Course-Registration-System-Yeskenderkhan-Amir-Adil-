package kz.university.studentcourse.controller;

import jakarta.servlet.http.HttpSession;
import kz.university.studentcourse.entity.Course;
import kz.university.studentcourse.entity.Student;
import kz.university.studentcourse.repository.CourseRepository;
import kz.university.studentcourse.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentController(StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @GetMapping("/")
    public String showLogin() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String name, HttpSession session) {
        Student student = studentRepo.findByEmail(email);
        if (student == null) {
            student = new Student(name, email);
            studentRepo.save(student);
        }
        session.setAttribute("user", student);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // === FIX: Всегда обновляем студента из базы ===
    private Student getFreshUser(HttpSession session) {
        Student sessionUser = (Student) session.getAttribute("user");
        if (sessionUser == null) return null;
        return studentRepo.findById(sessionUser.getId()).orElse(null);
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Student user = getFreshUser(session); // Получаем свежие данные
        if (user == null) return "redirect:/";

        model.addAttribute("student", user);
        model.addAttribute("credits", user.getCurrentCredits());
        return "home";
    }

    @GetMapping("/courses")
    public String listCourses(Model model, HttpSession session) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";

        List<Course> allCourses = courseRepo.findAll();

        // Группируем курсы по категориям (CS, Math, etc.)
        Map<String, List<Course>> groupedCourses = allCourses.stream()
                .sorted(Comparator.comparing(Course::getId)) // Сортировка по ID
                .collect(Collectors.groupingBy(Course::getCategory));

        model.addAttribute("groupedCourses", groupedCourses);
        model.addAttribute("student", user);
        model.addAttribute("currentCredits", user.getCurrentCredits());
        return "course_list";
    }

    @GetMapping("/help")
    public String help(HttpSession session, Model model) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";
        model.addAttribute("student", user);
        return "help";
    }

    // === ЛОГИКА ЗАПИСИ ===
    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, HttpSession session) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";

        Course course = courseRepo.findById(courseId).orElse(null);
        if (course == null) return "redirect:/courses";

        // 1. Проверка лимита (20 кредитов)
        if (user.getCurrentCredits() + course.getCredits() > 20) {
            return "redirect:/courses?error=credits";
        }

        // 2. Проверка пререквизитов (Есть ли базовый курс?)
        if (course.getPrerequisite() != null && !user.getCourses().contains(course.getPrerequisite())) {
            return "redirect:/courses?error=prereq";
        }

        // 3. Стандартные проверки (места и повторная запись)
        if (course.hasSeats() && !user.getCourses().contains(course)) {
            user.getCourses().add(course);
            course.getStudents().add(user); // Для связи
            // Важно: уменьшаем места
            course.setSeats(course.getSeats() - 1);

            studentRepo.save(user);
            courseRepo.save(course);
        }

        return "redirect:/courses";
    }
}