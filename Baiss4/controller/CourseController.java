package Baiss4.controller;

import Baiss4.Enum.CourseStatus;
import Baiss4.request.CourseCreateRequest;
import Baiss4.request.CourseUpdateRequest;
import Baiss4.response.ApiResponse;
import Baiss4.response.CourseResponse;
import Baiss4.response.CourseResponseV2;
import Baiss4.response.PageResponse;
import Baiss4.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);
        return ResponseEntity.ok(new ApiResponse<>("Create course success", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id,
                                                    @RequestBody CourseUpdateRequest req) {
        courseService.updateCourse(id, req);
        return ResponseEntity.ok(new ApiResponse<>("Update course success", null));
    }

    @GetMapping("/courses/filter")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getCoursesByStatus(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status
    )
    {
        return ResponseEntity.ok(new ApiResponse<>(true,"Get courses by status successfully",courseService.getPagedCourses(page, size, sortBy, direction, status)));
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseV2>> getAllCoursesV2(
            @RequestParam CourseStatus status,
            Pageable pageable) {

        Page<CourseResponseV2> response = courseService.getCoursesByStatus(status, pageable);
        return ResponseEntity.ok(response);
    }
}
