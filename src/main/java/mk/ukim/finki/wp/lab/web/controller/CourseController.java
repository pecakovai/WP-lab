package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Course> courseList = this.courseService.findAll().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
        model.addAttribute("courses",courseList);

        return "listCourses";
    }


    @PostMapping("/add")
    public String saveCourse(Model model,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long teacherId
    ) {
        Course course = this.courseService.save(name, description, teacherId);

        if (course != null) {

            return "redirect:/course";
        }else{
            model.addAttribute("hasError", true);
            model.addAttribute("error", "This course exist");
            return "add-course";
        }
    }


    @GetMapping("/add-form")
    public String getAddCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAll();

        model.addAttribute("teachers", teachers);

        return "add-course";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {

        if (this.courseService.findById(id) != null) {
            Course course = this.courseService.findById(id);

            List<Teacher> teachers = this.teacherService.findAll();

            model.addAttribute("teachers", teachers);
            model.addAttribute("course", course);


            return "add-course";
        }
        return "redirect:/course?error=CourseNotFound";
    }

    @GetMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.courseService.deleteById(id);

        return "redirect:/course";
    }
}
