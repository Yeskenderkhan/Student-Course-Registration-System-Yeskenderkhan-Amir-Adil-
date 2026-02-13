package kz.university.studentcourse.controller;

import jakarta.servlet.http.HttpSession;
import kz.university.studentcourse.entity.Course;
import kz.university.studentcourse.entity.Student;
import kz.university.studentcourse.repository.CourseRepository;
import kz.university.studentcourse.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentController(StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    // ==========================================
    // 1. АВТОРИЗАЦИЯ И РЕГИСТРАЦИЯ
    // ==========================================

    @GetMapping("/")
    public String showLogin(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Student student = studentRepo.findByEmail(email);

        if (student != null && student.getPassword().equals(password)) {
            session.setAttribute("user", student);
            return "redirect:/home";
        }
        return "redirect:/?error=true";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String major,
                           @RequestParam String academicYear,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        if (studentRepo.findByEmail(email) != null) {
            model.addAttribute("error", "Email is already registered!");
            return "register";
        }

        String fullName = firstName + " " + lastName;
        Student newStudent = new Student(fullName, email, password, major, academicYear);
        studentRepo.save(newStudent);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private Student getFreshUser(HttpSession session) {
        Student sessionUser = (Student) session.getAttribute("user");
        if (sessionUser == null) return null;
        return studentRepo.findById(sessionUser.getId()).orElse(null);
    }

    // ==========================================
    // 2. ОСНОВНЫЕ СТРАНИЦЫ
    // ==========================================

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";

        model.addAttribute("student", user);
        model.addAttribute("credits", user.getCurrentCredits());
        return "home";
    }

    @GetMapping("/courses")
    public String listCourses(Model model, HttpSession session, @RequestParam(required = false) String keyword) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";

        List<Course> courses = (keyword != null && !keyword.isEmpty())
                ? courseRepo.searchCourses(keyword)
                : courseRepo.findAll();

        Map<String, List<Course>> groupedCourses = courses.stream()
                .sorted(Comparator.comparing(Course::getId))
                .collect(Collectors.groupingBy(Course::getCategory));

        model.addAttribute("groupedCourses", groupedCourses);
        model.addAttribute("student", user);
        model.addAttribute("currentCredits", user.getCurrentCredits());
        model.addAttribute("keyword", keyword);
        return "course_list";
    }

    // ==========================================
    // 3. ЗАПИСЬ (ENROLL / DROP)
    // ==========================================

    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, HttpSession session) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";
        Course course = courseRepo.findById(courseId).orElse(null);
        if (course == null) return "redirect:/courses";

        if (user.getCurrentCredits() + course.getCredits() > 30) return "redirect:/courses?error=credits";

        if (course.getPrerequisite() != null) {
            boolean hasPrereq = user.getCourses().contains(course.getPrerequisite());
            if (!hasPrereq) return "redirect:/courses?error=prereq";
            if (hasPrereq) return "redirect:/courses?error=simultaneous";
        }

        for (Course existing : user.getCourses()) {
            if (isTimeConflict(existing.getSchedule(), course.getSchedule())) {
                return "redirect:/courses?error=time_conflict";
            }
        }

        if (course.hasSeats() && !user.getCourses().contains(course)) {
            user.getCourses().add(course);
            course.getStudents().add(user);
            course.setSeats(course.getSeats() - 1);

            studentRepo.save(user);
            courseRepo.save(course);
            return "redirect:/courses#course-" + courseId;
        }
        return "redirect:/courses";
    }

    @PostMapping("/drop/{courseId}")
    public String drop(@PathVariable Long courseId, HttpSession session) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";
        Course course = courseRepo.findById(courseId).orElse(null);

        if (course != null && user.getCourses().contains(course)) {
            user.getCourses().remove(course);
            course.getStudents().remove(user);
            course.setSeats(course.getSeats() + 1);

            studentRepo.save(user);
            courseRepo.save(course);
            return "redirect:/courses#course-" + courseId;
        }
        return "redirect:/courses";
    }

    // ==========================================
    // 4. ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ
    // ==========================================

    private boolean isTimeConflict(String schedule1, String schedule2) {
        try {
            String[] parts1 = schedule1.split(" ");
            String[] parts2 = schedule2.split(" ");

            boolean commonDay = false;
            for (String d1 : parts1[0].split("/")) {
                for (String d2 : parts2[0].split("/")) {
                    if (d1.equals(d2)) { commonDay = true; break; }
                }
            }
            if (!commonDay) return false;

            int[] range1 = parseTimeRange(parts1[1]);
            int[] range2 = parseTimeRange(parts2[1]);
            return (range1[0] < range2[1] && range1[1] > range2[0]);

        } catch (Exception e) { return false; }
    }

    private int[] parseTimeRange(String timeRange) {
        String[] times = timeRange.split("-");
        return new int[] { toMinutes(times[0]), toMinutes(times[1]) };
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // ==========================================
    // 5. GRADES & CALENDAR
    // ==========================================

    @GetMapping("/grades")
    public String grades(Model model, HttpSession session) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";
        model.addAttribute("student", user);
        return "grades";
    }

    @GetMapping("/calendar")
    public String calendar(Model model, HttpSession session) {
        Student user = getFreshUser(session);
        if (user == null) return "redirect:/";
        model.addAttribute("student", user);

        Map<Integer, List<CalendarEvent>> scheduleMap = new HashMap<>();
        for (int day = 1; day <= 28; day++) {
            LocalDate date = LocalDate.of(2026, 2, day);
            String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

            List<CalendarEvent> dailyEvents = new ArrayList<>();
            for (Course c : user.getCourses()) {
                if (c.getSchedule().split(" ")[0].contains(dayOfWeek)) {
                    String time = c.getSchedule().split(" ")[1];
                    dailyEvents.add(new CalendarEvent(c.getTitle(), time, "bg-primary"));
                }
            }
            // ЗДЕСЬ РАНЬШЕ БЫЛ "Add/Drop End". Я ЕГО УДАЛИЛ.

            scheduleMap.put(day, dailyEvents);
        }

        java.time.LocalDate today = java.time.LocalDate.now();
        model.addAttribute("currentDay", today.getDayOfMonth());
        model.addAttribute("scheduleMap", scheduleMap);
        return "calendar";
    }

    public static class CalendarEvent {
        public String title, time, color;
        public CalendarEvent(String title, String time, String color) {
            this.title = title; this.time = time; this.color = color;
        }
    }
}