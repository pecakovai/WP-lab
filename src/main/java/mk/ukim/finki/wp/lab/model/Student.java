package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
public class Student {

    @Id
    String username;
    String password;
    String name;
    String surname;

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getUsername(), student.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }
}
