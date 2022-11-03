package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
   public List<Course> findAllCourses(){
        return DataHolder.courseList;
    }
    public Course findById(Long courseId){
        return DataHolder.courseList.stream()
                .filter(c -> c.getCourseId() == courseId).toList().get(0);
    }
    public List<Student> findAllStudentByCourse(Long courseId){
        Course course = DataHolder.courseList.stream()
                .filter(c -> c.getCourseId() == courseId).toList().get(0);
        return course.getStudents();
    }
    public Course addStudentToCourse(Student student,Course course){
        DataHolder.courseList.stream()
                .filter(c -> c.getCourseId() == course.getCourseId()).toList().get(0)
                .getStudents().add(student);
        return course;
    }
}
