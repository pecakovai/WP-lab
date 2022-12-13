package mk.ukim.finki.wp.lab.service;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Long id);
    List<Student> listStudentByCourse(Long courseId);
    Course addStudentInCourse(String username,Long courseId) throws Exception;

    Course save(String name, String description, Long teacherId, Type type);
    Course update(Long id,String name,String description,Long teacherId,Type type);


    void deleteById(Long id);
}
