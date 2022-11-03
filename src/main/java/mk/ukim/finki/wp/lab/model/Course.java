package mk.ukim.finki.wp.lab.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    Long courseId;
    String name;
    String description;
    List<Student> students;

    public Course() {
    }

    public Course(Long courseId, String name, String description) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public List<Student> getStudents() {
        return students;
    }
}
