package mk.ukim.finki.wp.lab.service.impl;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;



    public GradeServiceImpl(GradeRepository gradeRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Grade save(Character grade, LocalDateTime timestamp, String idStudent, Long idCourse) {
        Student student = this.studentRepository.findByUsernameContainingIgnoreCase(idStudent);
        Course course = this.courseRepository.findById(idCourse).orElseThrow();
        Grade g = this.gradeRepository.findAll().stream().filter(gs -> gs.getCourse().equals(course) && gs.getStudent().equals(student)).toList().get(0);
        g.setGrade(grade);
        g.setTimestamp(timestamp);
        return this.gradeRepository.save(g);

    }

    @Override
    public List<Grade> findByCourse(Course course) {
        return this.gradeRepository.findAllByCourse(course).stream().sorted().toList();
    }

}
