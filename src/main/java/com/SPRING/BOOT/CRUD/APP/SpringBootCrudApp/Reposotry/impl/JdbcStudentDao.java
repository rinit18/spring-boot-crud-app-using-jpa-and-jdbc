package com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Reposotry.impl;


import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Model.Student;
import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Reposotry.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("jdbcDao")
public class JdbcStudentDao implements StudentDao {

    private final JdbcTemplate jdbc;

    private static final RowMapper<Student> STUDENT_ROW_MAPPER = (rs, rowNum) ->
            new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("marks")
            );

    @Autowired
    public JdbcStudentDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Student save(Student student) {
        String sql = "INSERT INTO student (name, marks) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getMarks());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            student.setId(keyHolder.getKey().intValue());
        }

        return student;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        return jdbc.query(sql, STUDENT_ROW_MAPPER);
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        try {
            return jdbc.queryForObject(sql, STUDENT_ROW_MAPPER, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        jdbc.update(sql, id);
    }
}
