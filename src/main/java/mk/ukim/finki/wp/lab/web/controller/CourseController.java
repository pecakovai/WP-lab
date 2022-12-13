package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    private final TeacherService teacherService;

    private final StudentService studentService;

    private final GradeService gradeService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Course> courseList = this.courseService.findAll().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
        model.addAttribute("courses",courseList);

        model.addAttribute("bodyContent", "listCourses");
        return "master-template";
    }

    @GetMapping("/{id}/add-student")
    public String getAddStudent(@PathVariable Long id, @RequestParam(required = false) String error, Model model, HttpServletRequest req){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Student> list = this.studentService.listAll();

        model.addAttribute("students",list);
        model.addAttribute("courseId",id);
        req.getSession().setAttribute("courseId",id);


        model.addAttribute("bodyContent", "listStudents");
        return "master-template";

    }
    @PostMapping("{id}/add-student-to-course")
    public String addStudentToCourse(@PathVariable Long id, @RequestParam String studentUsername, @RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        try {
            this.courseService.addStudentInCourse(studentUsername,id);
            return "redirect:/course/{id}";

        } catch (Exception e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "This username already exist in this course");
            model.addAttribute("username", studentUsername);
            model.addAttribute("courseId",id);

            model.addAttribute("bodyContent", "listStudents");

            return "master-template";


        }


    }

    @GetMapping("{id}")
    public String getCoursesPage(@PathVariable Long id,@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Student> list= this.courseService.listStudentByCourse(id);
        Course course = this.courseService.findById(id);
        List<Grade> grades = this.gradeService.findByCourse(course);
        model.addAttribute("studentsInCourse",grades);
        model.addAttribute("courseName",course.getName());
        model.addAttribute("courseId",course.getCourseId());

        model.addAttribute("bodyContent", "studentsInCourse");
        return "master-template";
    }


    @PostMapping("/add")
    public String saveCourse(@RequestParam(required = false) Long id, Model model,
            @RequestParam String name,
            @RequestParam String description, @RequestParam String type,
            @RequestParam Long teacherId
    ) {
        Type type1 = Type.valueOf(type);
        Course course = null;

        if (id != null){
            course = this.courseService.update(id,name,description,teacherId,type1);
        }else{
            course = this.courseService.save(name, description, teacherId,type1);
        }

        if (course != null) {

            return "redirect:/course";
        }else{
            model.addAttribute("hasError", true);
            model.addAttribute("error", "This course exist");
            model.addAttribute("bodyContent", "add-course");
            return "master-template";
        }
    }


    @GetMapping("/add-form")
    public String getAddCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAll();

        model.addAttribute("teachers", teachers);
        model.addAttribute("types", Type.values());

        model.addAttribute("bodyContent", "add-course");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {

        if (this.courseService.findById(id) != null) {
            Course course = this.courseService.findById(id);

            List<Teacher> teachers = this.teacherService.findAll();

            model.addAttribute("teachers", teachers);
            model.addAttribute("course", course);
            model.addAttribute("types", Type.values());


            model.addAttribute("bodyContent", "add-course");
            return "master-template";
        }
        return "redirect:/course?error=CourseNotFound";
    }

    @GetMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.courseService.deleteById(id);

        return "redirect:/course";
    }
}
