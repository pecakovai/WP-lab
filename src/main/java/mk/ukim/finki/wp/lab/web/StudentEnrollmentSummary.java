package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentEnrollmentSummaryServlet",urlPatterns = "/StudentEnrollmentSummary")

public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final StudentService studentService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String username = req.getParameter("size");
        String id = String.valueOf(req.getSession().getAttribute("courseId"));

        Course course = this.courseService.findById(Long.parseLong(id));
        this.courseService.addStudentInCourse(username,Long.parseLong(id));
        List<Student> studentList = this.courseService.listStudentByCourse(course.getCourseId());
        context.setVariable("username",username);
        context.setVariable("courseName",course.getName());
        context.setVariable("studentsInCourse",studentList);
        req.getSession().setAttribute("username",username);


        springTemplateEngine.process("studentsInCourse.html",context,resp.getWriter());
    }


}
