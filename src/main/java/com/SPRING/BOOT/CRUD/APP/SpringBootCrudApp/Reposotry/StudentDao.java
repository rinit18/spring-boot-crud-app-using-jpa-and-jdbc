package com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Reposotry;



import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Model.Student;

import java.util.List;

public interface StudentDao {

    Student save(Student student);
    List<Student> findAll();
    Student findById(int id);
    void deleteById(int id);
}
