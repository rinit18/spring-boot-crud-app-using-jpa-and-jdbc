package com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Reposotry.impl;


import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
}
