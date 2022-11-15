package mk.ukim.finki.wp.lab.model;

public class Teacher {

    private Long id;
    private String name;
    private String surname;


    public Teacher(String name, String surname) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
