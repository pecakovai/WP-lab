package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;

import java.time.LocalDateTime;
import java.util.List;

public interface GradeService {

    List<Grade> findByCourse(Course course);

    Grade save(Character grade, LocalDateTime timestamp,String idStudent,Long idCourse);
}
