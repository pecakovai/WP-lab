package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByUsernameContainingIgnoreCase(String username);
    List<Student> findAllByNameContainingIgnoreCaseOrAndSurnameContainingIgnoreCase(String text,String text2);
}
