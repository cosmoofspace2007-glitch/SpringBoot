package Baiss4.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instructor")
@Entity
public class Instructor
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false,length = 100)
  private String name;
  private String email;

  @OneToMany(mappedBy = "instructor")
  private List<Course> courses;
}
