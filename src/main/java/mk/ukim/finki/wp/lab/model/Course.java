package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;
    String name;
    String description;

    private Type type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getName(), course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @ManyToOne
    Teacher teacher;
    @ManyToMany
    List<Student> students;

    public Course() {
    }
    public Course(String name, String description,Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public Course(String name, String description,Teacher teacher,Type type) {
        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.students = new ArrayList<>();
        this.type = type;
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

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
