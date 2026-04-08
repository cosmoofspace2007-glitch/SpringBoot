package Baiss4.controller;

import Baiss4.response.ApiResponse;
import Baiss4.service.StudentEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students-enrollments")
public class StudentEnrollmentController {

    private final StudentEnrollmentService service;

    public StudentEnrollmentController(StudentEnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enroll(@RequestParam Long studentId,
                                                    @RequestParam Long courseId) {
        service.enrollStudent(studentId, courseId);
        return ResponseEntity.ok(new ApiResponse<>("Enroll success", null));
    }
}
