package service.Custom;

import dto.StudentDto;
import service.SuperService;

import java.util.ArrayList;

public interface StudentService extends SuperService {
    public String saveStudent(StudentDto studentDto) throws Exception;
    public String updateStudent(StudentDto studentDto) throws Exception;
    public String deleteStudent(String StudentId) throws Exception;
    public StudentDto searchStudent(String StudentId) throws Exception;
    public ArrayList<StudentDto> getAllStudent() throws Exception;
}
