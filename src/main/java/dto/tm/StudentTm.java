package dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTm {
    private String student_id;
    private String name;
    private String reg_number;
    private String gender;
    private Date dob;
    private String email;
    private String contact;
    private String course_id;
}
