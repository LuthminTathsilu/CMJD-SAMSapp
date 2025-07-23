package service.Custom.impl;
import dto.AttendenceDto;
import dto.CourseDto;
import entity.AttendenceEntity;
import entity.CourseEntity;
import entity.StudentEntity;
import service.Custom.AttendenceService;

import java.util.ArrayList;

public class AttendenceServiceImpl implements AttendenceService {


    @Override
    public String saveAttendence(AttendenceDto attendenceDto) throws Exception {
        AttendenceEntity attendenceEntity = new AttendenceEntity(
                attendenceDto.getId(),
                attendenceDto.getStudentId(),
                attendenceDto.getClassId(),
                attendenceDto.getStatus(),
                attendenceDto.getMarkedTime()
                );
        boolean isSaved = true;
        return isSaved ? "Succes" : "fail";
    }

    @Override
    public String updateAttendence(AttendenceDto attendenceDto) throws Exception {
        AttendenceEntity attendenceEntity = new AttendenceEntity(
                attendenceDto.getId(),
                attendenceDto.getStudentId(),
                attendenceDto.getClassId(),
                attendenceDto.getStatus(),
                attendenceDto.getMarkedTime()
        );
        boolean isUpdated = true;
        return isUpdated ? "Succes" : "fail";
    }

    @Override
    public AttendenceDto searchAttendence(String attendenceId) throws Exception {
        AttendenceEntity attendenceEntity = null;
        if(attendenceEntity != null){
            return new AttendenceDto(attendenceEntity.getId(),
                        attendenceEntity.getStudentId(),
                    attendenceEntity.getClassId(),
                    attendenceEntity.getStatus(),
                    attendenceEntity.getMarkedTime());
        }
        return null;
    }

    @Override
    public ArrayList<AttendenceDto> getAllAttendence() throws Exception {
        ArrayList<AttendenceEntity> attendenceEntities = null;
        ArrayList<AttendenceDto> attendenceDtos = new ArrayList<>();
        if (attendenceDtos != null){
            for (AttendenceEntity attendenceEntity : attendenceEntities){
                attendenceDtos.add(new AttendenceDto(attendenceEntity.getId(),
                        attendenceEntity.getStudentId(),
                        attendenceEntity.getClassId(),
                        attendenceEntity.getStatus(),
                        attendenceEntity.getMarkedTime()));
            }
        }
        return attendenceDtos;
    }
}
