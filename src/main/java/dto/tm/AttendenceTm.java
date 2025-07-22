package dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendenceTm {
    private String id;
    private String studentId;
    private String classId;
    private String status;
    private String markedTime;



}
