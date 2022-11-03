package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAllCourses();
    }

    @Override
    public Course findById(Long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    public List<Student> listStudentByCourse(Long courseId) {
        Course course = this.courseRepository.findById(courseId);
        return course.getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = this.studentRepository.findStudentByUsername(username);
        Course course = this.courseRepository.findById(courseId);

        this.courseRepository.addStudentToCourse(student,course);
        return course;
    }
}
