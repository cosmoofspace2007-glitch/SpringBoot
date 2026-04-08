package Baiss5;

import Baiss4.Enum.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseResponse
{
    private Long id;
    private String title;
    private CourseStatus status;
}
