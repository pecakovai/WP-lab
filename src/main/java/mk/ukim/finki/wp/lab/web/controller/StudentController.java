package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")

    public String getAllStudent(Model model){
        List<Student> list = this.studentService.listAll();
        model.addAttribute("students",list);

        return "listStudents";
    }

    @GetMapping("create-student")
    public String createStudent(Model model){
        model.addAttribute("bodyContent", "addStudent");
        return "master-template";
    }

    @PostMapping("create-student")
    public String createStudent(@RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam String surname, HttpServletRequest req){
        this.studentService.save(username,password,name,surname);
        Long id = (Long) req.getSession().getAttribute("courseId");
        if (id != null) {
            return "redirect:/course/" + id + "/add-student";
        }else{
            return "redirect:/create-student";
        }
    }
}
