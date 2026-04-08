package Baiss4.controller;

import Baiss4.response.ApiResponse;
import Baiss4.repository.StudentRepository;
import Baiss4.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(new ApiResponse<>("Create student success", null));
    }
}
