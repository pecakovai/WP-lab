package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return this.studentRepository.findAllStudents();
    }

    @Override
    public Student findStudentByUsername(String username) {
        return this.studentRepository.findStudentByUsername(username);
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return this.studentRepository.findAllStudents();
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        Student student = new Student(username,password,name,surname);
        this.studentRepository.saveStudent(student);
        return student;
    }
}
