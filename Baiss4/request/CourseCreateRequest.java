package Baiss4.request;

import Baiss4.Enum.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateRequest
{
    private String title;
    private CourseStatus status;
    private Long instructorId;

}
