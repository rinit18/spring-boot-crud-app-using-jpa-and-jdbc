package com.rinit.springbootcrud.Reposotry;



import com.rinit.springbootcrud.Model.Student;

import java.util.List;

public interface StudentDao {

    Student save(Student student);
    List<Student> findAll();
    Student findById(int id);
    void deleteById(int id);
}
