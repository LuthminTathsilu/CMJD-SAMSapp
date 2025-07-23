package service.Custom.impl;

import dao.Custom.CourseDao;
import dao.Custom.StudentDao;
import dao.DaoFactory;
import dto.CourseDto;
import dto.StudentDto;
import entity.CourseEntity;
import entity.StudentEntity;
import service.Custom.CourseService;

import java.util.ArrayList;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = (CourseDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.COURSE);

    @Override
    public String saveCourse(CourseDto courseDto) throws Exception {
        CourseEntity courseEntity = new CourseEntity(
                courseDto.getCourse_id(),
                courseDto.getName(),
                courseDto.getDescription(),
                courseDto.getDurationWeeks());
        boolean isSaved = courseDao.save(courseEntity);
        return isSaved ? "Succes" : "fail";
    }

    @Override
    public String updateCourse(CourseDto courseDto) throws Exception {
        CourseEntity courseEntity = new CourseEntity(
                courseDto.getCourse_id(),
                courseDto.getName(),
                courseDto.getDescription(),
                courseDto.getDurationWeeks());
        boolean isUpdated = courseDao.update(courseEntity);
        return isUpdated ? "Succes" : "fail";
    }

    @Override
    public String deleteCourse(String CourseId) throws Exception {
        boolean isDeleted = courseDao.delete(CourseId);
        return isDeleted ? "Succes" : "Fail";
    }

    @Override
    public CourseDto searchCourse(String CourseId) throws Exception {
        CourseEntity courseEntity = courseDao.search(CourseId);
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
        ArrayList<CourseEntity> courseEntities = courseDao.getALL();
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
