package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();

    @PostConstruct
    public void init(){
        courseList.add(new Course(Long.parseLong("1"),"Web programming","Web programming course"));
        courseList.add(new Course(Long.parseLong("2"),"Operative system","Operative system course"));
        courseList.add(new Course(Long.parseLong("3"),"Electronic and mobile commerce","Electronic and mobile commerce course"));
        courseList.add(new Course(Long.parseLong("4"),"Computer networks","Computer networks course"));

        studentList.add(new Student("petar.petrov","1234","Petar","Petrov"));
        studentList.add(new Student("mile.milev","1234","Mile","Milev"));
        studentList.add(new Student("gjorgji.gjorgjiev","1234","Gjorgji","Gjorgjiev"));

    /* for (int i = 1; i <= 10; i++){
            studentList.add(new Student(String.format("Student%d",i),
                    String.format("Student%d",i),
                    String.format("Student%d",i),
                    String.format("Student%d",i)));
        }*/
    }
}
