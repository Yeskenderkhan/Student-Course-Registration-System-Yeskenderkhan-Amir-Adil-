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

    // 1. Страница логина
    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    // 2. Обработка входа (Простая имитация безопасности)
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String name, HttpSession session) {
        Student student = studentRepo.findByEmail(email);
        if (student == null) {
            // Если студента нет - создаем нового
            student = new Student(name, email);
            studentRepo.save(student);
        }
        // Сохраняем ID студента в сессии (он "залогинен")
        session.setAttribute("user", student);
        return "redirect:/courses";
    }

    // 3. Список курсов
    @GetMapping("/courses")
    public String listCourses(Model model, HttpSession session) {
        Student user = (Student) session.getAttribute("user");
        if (user == null) return "redirect:/"; // Если не залогинен - на выход

        // Обновляем данные студента из БД, чтобы видеть актуальные курсы
        user = studentRepo.findById(user.getId()).orElse(null);

        List<Course> allCourses = courseRepo.findAll();

        model.addAttribute("courses", allCourses);
        model.addAttribute("student", user);
        return "course_list";
    }

    // 4. Логика записи на курс (САМОЕ ВАЖНОЕ)
    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, HttpSession session) {
        Student user = (Student) session.getAttribute("user");
        if (user == null) return "redirect:/";

        Course course = courseRepo.findById(courseId).orElse(null);
        user = studentRepo.findById(user.getId()).orElse(null);

        // Проверяем: есть ли места И не записан ли уже студент
        if (course != null && user != null && course.getSeats() > 0 && !user.getCourses().contains(course)) {
            // Уменьшаем количество мест
            course.setSeats(course.getSeats() - 1);
            courseRepo.save(course);

            // Добавляем курс студенту
            user.getCourses().add(course);
            studentRepo.save(user);
        }

        return "redirect:/courses";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}