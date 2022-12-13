package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

@Data
@Entity
public class Grade implements Comparable<Grade>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character grade;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public Grade(Character grade, Student student, Course course,LocalDateTime timestamp) {
        this.grade = grade;
        this.student = student;
        this.course = course;
        this.timestamp = timestamp;
    }

    public Grade(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Grade() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade grade = (Grade) o;
        return Objects.equals(getTimestamp(), grade.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp());
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Grade o) {
        return this.id.compareTo(o.id);
    }
}
