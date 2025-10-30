package com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Reposotry.impl;



import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Model.Student;
import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Reposotry.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpaDao")
public class JpaStudentDAO implements StudentDao {

    @Autowired
    private StudentJpaRepository repo;

    @Override
    public Student save(Student student) {
        return repo.save(student);
    }

    @Override
    public List<Student> findAll() {
        return repo.findAll();
    }

    @Override
    public Student findById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}