package com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Service;



import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Model.Student;
import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Reposotry.StudentDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer that handles business logic for Student entities.
 * It delegates data access to a DAO (either JDBC or JPA implementation),
 * making the architecture loosely coupled and easily switchable.
 */
@Service
public class StudentService {

    private final StudentDao studentDAO;

    /**
     * Constructor-based dependency injection.
     * Use @Qualifier to choose between "jdbcDAO" or "jpaDAO".
     *
     * Example:
     *  - @Qualifier("jdbcDao") → use manual SQL (Spring JDBC)
     *  - @Qualifier("jpaDao")  → use Spring Data JPA repository
     */
    public StudentService(@Qualifier("jpaDao") StudentDao studentDAO) {
        this.studentDAO = studentDAO;
    }

    /**
     * Adds or updates a student in the database.
     *
     * @param student The student entity to be saved.
     * @return The saved student.
     */
    public Student addStudent(Student student) {
        return studentDAO.save(student);
    }

    /**
     * Retrieves all students from the database.
     *
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    /**
     * Retrieves a specific student by their ID.
     *
     * @param id The ID of the student to retrieve.
     * @return An Optional containing the student if found, otherwise empty.
     */
    public Optional<Student> getStudentById(int id) {
        return Optional.ofNullable(studentDAO.findById(id));
    }

    /**
     * Updates an existing student record.
     * Uses Optional to handle cases where student might not exist.
     *
     * @param student The updated student entity.
     * @return An Optional containing the updated student or empty if not found.
     */
    public Optional<Student> updateStudent(Student student) {
        Student updated = studentDAO.save(student); // save() can handle both insert/update
        return Optional.ofNullable(updated);
    }

    /**
     * Deletes a student by their ID.
     *
     * @param id The ID of the student to delete.
     */
    public void deleteStudent(int id) {
        studentDAO.deleteById(id);
    }
}
