package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class GradeController {
    private final GradeService gradeService;
    private final CourseService courseService;

    public GradeController(GradeService gradeService, CourseService courseService) {
        this.gradeService = gradeService;
        this.courseService = courseService;
    }

    @GetMapping("grade/{idCourse}/{idStudent}")
    public String addGrade(@PathVariable Long idCourse, @PathVariable String idStudent, Model model){
        model.addAttribute("bodyContent", "add-grade");
        model.addAttribute("idCourse",idCourse);
        model.addAttribute("idStudent",idStudent);
        return "master-template";
    }

    @PostMapping("grade/{idCourse}/{idStudent}")
    public String addGrade(@PathVariable Long idCourse, @PathVariable String idStudent, @RequestParam Character grade, @RequestParam String date){
        this.gradeService.save(grade,LocalDateTime.parse(date),idStudent,idCourse);
        return "redirect:/course/{idCourse}";
    }
}
