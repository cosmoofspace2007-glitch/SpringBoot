package Baiss4.controller;

import Baiss4.response.ApiResponse;
import Baiss4.request.InstructorCreateRequest;
import Baiss4.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody InstructorCreateRequest req) {
        instructorService.createInstructor(req);
        return ResponseEntity.ok(new ApiResponse<>("Create instructor success", null));
    }
}
