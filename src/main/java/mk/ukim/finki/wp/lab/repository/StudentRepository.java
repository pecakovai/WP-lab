package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    public List<Student> findAllStudents(){
        return DataHolder.studentList;
    }
    public List<Student> findAllByNameOrSurname(String text){
        return DataHolder.studentList.stream()
                .filter(s -> s.getName().equals(text) || s.getSurname().equals(text)).collect(Collectors.toList());
    }
    public Student findStudentByUsername(String text){
        return DataHolder.studentList.stream()
                .filter(s -> s.getUsername().equals(text)).toList().get(0);
    }


    public Student saveStudent(Student student){
         DataHolder.studentList.add(student);
         return student;
    }
}
