package com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Controller;


import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Model.Student;
import com.SPRING.BOOT.CRUD.APP.SpringBootCrudApp.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*") // ✅ **FIX ADDED HERE**
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // GET student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
        Optional<Student> student = service.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    // PUT (update) a student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student studentDetails) {
        // Ensure the ID in the path matches the ID in the body, if present
        if (studentDetails.getId() != 0 && studentDetails.getId() != id) {
            return ResponseEntity.badRequest().build();
        }
        studentDetails.setId(id); // Set the ID from the path

        Optional<Student> updatedStudent = service.updateStudent(studentDetails);
        return updatedStudent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
        if (service.getStudentById(id).isPresent()) {
            service.deleteStudent(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

