package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseEntity {
    private String course_id;
    private String name;
    private String description;
    private int durationWeeks;
}
