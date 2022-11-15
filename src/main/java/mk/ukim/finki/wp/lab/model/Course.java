package mk.ukim.finki.wp.lab.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    Long courseId;
    String name;
    String description;

    Teacher teacher;
    List<Student> students;

    public Course() {
    }

    public Course(String name, String description,Teacher teacher) {
        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }
}
