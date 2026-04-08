package Baiss4.service;

import Baiss4.repository.StudentRepository;
import Baiss4.model.Course;
import Baiss4.model.Student;
import Baiss4.model.StudentEnrollment;
import Baiss4.repository.CourseRepository;
import Baiss4.repository.StudentEnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentEnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository enrollmentRepository;

    public StudentEnrollmentService(StudentRepository studentRepository,
                                    CourseRepository courseRepository,
                                    StudentEnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        enrollmentRepository.save(enrollment);
    }
}
