package service.Custom.impl;

import dto.CourseDto;
import dto.StudentDto;
import entity.CourseEntity;
import entity.StudentEntity;
import service.Custom.CourseService;

import java.util.ArrayList;

public class CourseServiceImpl implements CourseService {

    @Override
    public String saveCourse(CourseDto courseDto) throws Exception {
        CourseEntity courseEntity = new CourseEntity(
                courseDto.getCourse_id(),
                courseDto.getName(),
                courseDto.getDescription(),
                courseDto.getDurationWeeks());
        boolean isSaved = true;
        return isSaved ? "Succes" : "fail";
    }

    @Override
    public String updateCourse(CourseDto courseDto) throws Exception {
        CourseEntity courseEntity = new CourseEntity(
                courseDto.getCourse_id(),
                courseDto.getName(),
                courseDto.getDescription(),
                courseDto.getDurationWeeks());
        boolean isUpdated = true;
        return isUpdated ? "Succes" : "fail";
    }

    @Override
    public String deleteCourse(String CourseId) throws Exception {
        boolean isDeleted = true;
        return isDeleted ? "Succes" : "Fail";
    }

    @Override
    public CourseDto searchCourse(String CourseId) throws Exception {
        CourseEntity courseEntity = null;
        if(courseEntity != null){
            return new CourseDto(courseEntity.getCourse_id(),
                    courseEntity.getName(),
                    courseEntity.getDescription(),
                    courseEntity.getDurationWeeks());
        }
        return null;
    }

    @Override
    public ArrayList<CourseDto> getAllCourse() throws Exception {
        ArrayList<CourseEntity> courseEntities = null;
        ArrayList<CourseDto> courseDtos = new ArrayList<>();
        if (courseEntities != null){
            for (CourseEntity courseEntity : courseEntities){
                courseDtos.add(new CourseDto(courseEntity.getCourse_id(),
                        courseEntity.getName(),
                        courseEntity.getDescription(),
                        courseEntity.getDurationWeeks()));
            }
        }
        return courseDtos;
    }
}
