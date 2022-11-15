package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
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

    @Override
    public Course save(String name, String description, Long teacherId) {
        Teacher teacher = this.teacherRepository.findById(teacherId);
        if (this.courseRepository.findAllCourses().stream().filter(c -> c.getName().equals(name)).toList().get(0) != null){
            return null;
        }else {
            return this.courseRepository.save(name, description, teacher);
        }
    }

    @Override
    public Course deleteById(Long id) {
        Course course = this.findById(id);
        this.courseRepository.deleteById(course);
        return course;
    }


}
