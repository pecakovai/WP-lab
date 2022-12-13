package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();

    public static List<Teacher> teacherList = new ArrayList<>();

    @PostConstruct
    public void init(){
        /*teacherList.add(new Teacher("Riste","Stojanov"));
        teacherList.add(new Teacher("Ana","Todorovska"));
        teacherList.add(new Teacher("Kostadin","Misev"));
        teacherList.add(new Teacher("Dimitar","Trajanov"));
        teacherList.add(new Teacher("Sasho","Gramatikov"));


        courseList.add(new Course("Web programming","Web programming course",teacherList.get(0)));
        courseList.add(new Course("Operative system","Operative system course",teacherList.get(1)));
        courseList.add(new Course("Electronic and mobile commerce","Electronic and mobile commerce course",teacherList.get(2)));
        courseList.add(new Course("Computer networks","Computer networks course",teacherList.get(3)));

        studentList.add(new Student("petar.petrov","1234","Petar","Petrov"));
        studentList.add(new Student("mile.milev","1234","Mile","Milev"));
        studentList.add(new Student("gjorgji.gjorgjiev","1234","Gjorgji","Gjorgjiev"));*/

    /* for (int i = 1; i <= 10; i++){
            studentList.add(new Student(String.format("Student%d",i),
                    String.format("Student%d",i),
                    String.format("Student%d",i),
                    String.format("Student%d",i)));
        }*/
    }
}
