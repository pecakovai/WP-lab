package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {



    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return this.courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Student> listStudentByCourse(Long courseId) {
        return this.findById(courseId).getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) throws Exception {
        Student student = this.studentRepository.findByUsernameContainingIgnoreCase(username);

        Course course = this.findById(courseId);
        System.out.println(course.getStudents().stream().anyMatch(s -> s.getUsername().equals(student.getUsername())));
        if (!course.getStudents().stream().anyMatch(s -> s.getUsername().equals(student.getUsername()))) {
            course.getStudents().add(student);
            this.courseRepository.save(course);
            Grade grade = new Grade(student, course);
            this.gradeRepository.save(grade);
        }else{
            throw new Exception("Student already exist in course");
        }

        return course;
    }

    @Override
    @Transactional
    public Course save(String name, String description, Long teacherId, Type type) {
        Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow();
        if (this.courseRepository.findByNameContainingIgnoreCase(name) == null) {
            Course course = new Course(name, description, teacher, type);
            return this.courseRepository.save(course);
        }else{
            return null;
        }

    }
    public Course update(Long id,String name, String description,Long teacherId,Type type) {
        Teacher teacher = this.teacherRepository.findById(teacherId).orElseThrow();
        Course course = this.findById(id);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacher);
        course.setType(type);
        return this.courseRepository.save(course);

    }


    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }


}
