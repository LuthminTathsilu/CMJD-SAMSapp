package service.Custom;

import dto.CourseDto;
import dto.StudentDto;
import service.SuperService;

import java.util.ArrayList;

public interface CourseService extends SuperService {
    public String saveCourse(CourseDto courseDto) throws Exception;
    public String updateCourse(CourseDto courseDto) throws Exception;
    public String deleteCourse(String CourseId) throws Exception;
    public CourseDto searchCourse(String CourseId) throws Exception;
    public ArrayList<CourseDto> getAllCourse() throws Exception;
}
