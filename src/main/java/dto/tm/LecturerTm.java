package dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LecturerTm {
    private String lecturer_id;
    private String name;
    private String qualification;
    private String department;
    private String email;
    private String contact;
}
