package Baiss5;

import Baiss4.Enum.CourseStatus;
import Baiss4.model.Instructor;
import Baiss4.model.StudentEnrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "course")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    @Column(nullable = false)
    private long instructorId;
    private String title;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "course")
    private List<StudentEnrollment> enrollments;

}
