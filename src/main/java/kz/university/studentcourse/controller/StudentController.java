package kz.university.studentcourse.controller;

import kz.university.studentcourse.entity.Student;
import kz.university.studentcourse.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("students", repository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("student", new Student());
        return "new_student";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        repository.save(student);
        return "redirect:/";
    }
}