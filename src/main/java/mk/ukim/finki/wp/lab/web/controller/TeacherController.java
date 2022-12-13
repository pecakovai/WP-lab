package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping("teachers")
    public String getTeachers(Model model){
        List<Teacher> list = this.teacherService.findAll();
        model.addAttribute("teachers",list);
        model.addAttribute("bodyContent", "teachers");
        return "master-template";
    }

    @GetMapping("teacher")
    public String addTeacher(Model model){
        model.addAttribute("bodyContent", "add-teacher");
        return "master-template";
    }

    @PostMapping("teacher")
    public String addTeacher(@RequestParam String name,@RequestParam String surname){
        this.teacherService.create(name,surname);

        return "redirect:/course";
    }
}
