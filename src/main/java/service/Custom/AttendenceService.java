package service.Custom;

import dto.AttendenceDto;
import dto.CourseDto;
import service.SuperService;

import java.util.ArrayList;

public interface AttendenceService extends SuperService {
    public String saveAttendence(AttendenceDto attendenceDto) throws Exception;
    public String updateAttendence(AttendenceDto attendenceDto) throws Exception;
    public AttendenceDto searchAttendence (String Id) throws Exception;
    public ArrayList<AttendenceDto> getAllAttendence() throws Exception;
}
