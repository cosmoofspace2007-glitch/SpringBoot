package Baiss4.repository;

import Baiss4.model.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long>
{

}
