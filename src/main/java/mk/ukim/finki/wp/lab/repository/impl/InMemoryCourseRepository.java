package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryCourseRepository {
   public List<Course> findAllCourses(){
        return DataHolder.courseList;
    }
    public Course findById(Long courseId){
        return DataHolder.courseList.stream()
                .filter(c -> Objects.equals(c.getCourseId(), courseId)).toList().get(0);
    }

    public Course findByName(String name){
        return DataHolder.courseList.stream()
                .filter(c -> Objects.equals(c.getName(), name)).toList().get(0);
    }
    public List<Student> findAllStudentByCourse(Long courseId){
        Course course = DataHolder.courseList.stream()
                .filter(c -> Objects.equals(c.getCourseId(), courseId)).toList().get(0);
        return course.getStudents();
    }
    public Course addStudentToCourse(Student student,Course course){
        DataHolder.courseList.stream()
                .filter(c -> c.getCourseId() == course.getCourseId()).toList().get(0)
                .getStudents().add(student);
        return course;
    }

    public Course save(String name,String description,Teacher teacher){
       Course course = new Course(name,description,teacher);
       DataHolder.courseList.add(course);
       return course;
    }

    public Course save(Long id,Course course){
       Course c = this.findById(id);
       DataHolder.courseList.remove(c);
       DataHolder.courseList.add(course);
        return course;
    }

    public Course deleteById(Course course){
       DataHolder.courseList.remove(course);
       return course;
    }
}
