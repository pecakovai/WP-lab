package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryTeacherRepository {

    public List<Teacher> findAll(){
        return DataHolder.teacherList;
    }

    public Teacher findById(Long id){
        return DataHolder.teacherList.stream().filter(t -> t.getId().equals(id)).toList().get(0);
    }

}
