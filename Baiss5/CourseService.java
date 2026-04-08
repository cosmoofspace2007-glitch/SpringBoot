package Baiss5;

import Baiss4.Enum.CourseStatus;
import Baiss4.model.Course;
import Baiss4.model.Instructor;
import Baiss4.repository.CourseRepository;
import Baiss4.repository.InstructorRepository;
import Baiss4.request.CourseCreateRequest;
import Baiss4.request.CourseUpdateRequest;
import Baiss4.response.CourseResponse;
import Baiss4.response.CourseResponseV2;
import Baiss4.response.PageResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class CourseService {

    private  final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository,
                         InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    // CREATE
    public void createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    // UPDATE
    public void updateCourse(Long id, CourseUpdateRequest req) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status)
    {
        if(page < 0)
            page = 0;

        if(sortBy == null || sortBy.isEmpty())
        {
            sortBy = "id";
        }

        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size,sort);

        Page<Course> coursePage = courseRepository.findAllByStatus(status,pageable);

        Page<CourseResponse> pr = coursePage.map(c -> {CourseResponse cr = new CourseResponse();
        cr.setTitle(c.getTitle());
        cr.setStatus(c.getStatus());
        cr.setId(c.getId());
        cr.setStatus(c.getStatus());
        return cr;});

         PageResponse<CourseResponse> response = new PageResponse<>();
         response.setItems(pr.getContent());
         response.setTotalPages(coursePage.getTotalPages());
         response.setPage(pr.getNumber());
         response.setSize(pr.getSize());
         response.setTotalItems((int) pr.getTotalElements());
         response.setLast(pr.isLast());
         return response;
    }
    public Page<CourseResponseV2> getCoursesByStatus(CourseStatus status, Pageable pageable)
    {
        return courseRepository.findAllByStatusV2(status, pageable);
    }
}
