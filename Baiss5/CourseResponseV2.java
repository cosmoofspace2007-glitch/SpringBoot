package Baiss5;

import Baiss4.Enum.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponseV2
{
    private Long id;
    private String title;
    private CourseStatus status;
}
