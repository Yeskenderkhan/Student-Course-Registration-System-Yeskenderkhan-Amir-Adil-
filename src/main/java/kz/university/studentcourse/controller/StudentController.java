package kz.university.studentcourse.controller;

import jakarta.servlet.http.HttpSession;
import kz.university.studentcourse.entity.Course;
import kz.university.studentcourse.entity.Student;
import kz.university.studentcourse.repository.CourseRepository;
import kz.university.studentcourse.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentController(StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    // === LOGIN PAGE ===
    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

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

    // === MENU TABS ===

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Student user = checkSession(session);
        if (user == null) return "redirect:/";

        model.addAttribute("student", user);
        return "home"; // Новая страница "Home"
    }

    @GetMapping("/help")
    public String help(HttpSession session, Model model) {
        Student user = checkSession(session);
        if (user == null) return "redirect:/";

        model.addAttribute("student", user);
        return "help"; // Новая страница "Help"
    }

    @GetMapping("/courses")
    public String listCourses(Model model, HttpSession session) {
        Student user = checkSession(session);
        if (user == null) return "redirect:/";

        // Обновляем данные студента из БД
        user = studentRepo.findById(user.getId()).orElse(null);

        List<Course> allCourses = courseRepo.findAll();

        model.addAttribute("courses", allCourses);
        model.addAttribute("student", user);
        return "course_list";
    }

    // === LOGIC ===

    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, HttpSession session) {
        Student user = checkSession(session);
        if (user == null) return "redirect:/";

        Course course = courseRepo.findById(courseId).orElse(null);
        user = studentRepo.findById(user.getId()).orElse(null);

        if (course != null && user != null) {
            // Проверка: есть ли места И не записан ли уже
            if (course.hasSeats() && !user.getCourses().contains(course)) {
                user.getCourses().add(course);
                course.getStudents().add(user); // Важно для связи ManyToMany

                studentRepo.save(user);
                courseRepo.save(course);
            }
        }
        return "redirect:/courses";
    }

    // Вспомогательный метод проверки входа
    private Student checkSession(HttpSession session) {
        return (Student) session.getAttribute("user");
    }
}