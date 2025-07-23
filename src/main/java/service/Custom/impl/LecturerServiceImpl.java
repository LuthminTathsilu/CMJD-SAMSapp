package service.Custom.impl;

import dao.Custom.LecturerDao;
import dao.Custom.StudentDao;
import dao.DaoFactory;
import dto.LecturerDto;
import dto.StudentDto;
import entity.LecturerEntity;
import entity.StudentEntity;
import service.Custom.LecturerService;

import java.util.ArrayList;

public class LecturerServiceImpl implements LecturerService {
    private LecturerDao lecturerDao = (LecturerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.LECTURER);

    @Override
    public String saveLecturer(LecturerDto lecturerDto) throws Exception {
        LecturerEntity lecturerEntity = new LecturerEntity(
                lecturerDto.getLecturer_id(),
                lecturerDto.getName(),
                lecturerDto.getQualification(),
                lecturerDto.getDepartment(),
                lecturerDto.getEmail(),
                lecturerDto.getContact());

        boolean isSaved = lecturerDao.save(lecturerEntity);
        return isSaved ? "Succes" : "fail";
    }

    @Override
    public String updateLecturer(LecturerDto lecturerDto) throws Exception {
        LecturerEntity lecturerEntity = new LecturerEntity(
                lecturerDto.getLecturer_id(),
                lecturerDto.getName(),
                lecturerDto.getQualification(),
                lecturerDto.getDepartment(),
                lecturerDto.getEmail(),
                lecturerDto.getContact());

        boolean isUpdated = lecturerDao.update(lecturerEntity);
        return isUpdated ? "Succes" : "fail";
    }

    @Override
    public String deleteLecturer(String lectureId) throws Exception {
        boolean isDeleted = lecturerDao.delete(lectureId);
        return isDeleted ? "Succes" : "Fail";
    }

    @Override
    public LecturerDto searchLecturer(String CourseId) throws Exception {
        LecturerEntity lecturerEntity = lecturerDao.search(CourseId);
        if(lecturerEntity != null){
            return new LecturerDto( lecturerEntity.getLecturer_id(),
                    lecturerEntity.getName(),
                    lecturerEntity.getQualification(),
                    lecturerEntity.getDepartment(),
                    lecturerEntity.getEmail(),
                    lecturerEntity.getContact());
        }
        return null;
    }

    @Override
    public ArrayList<LecturerDto> getAllLecturer() throws Exception {
        ArrayList<LecturerEntity> lecturerEntities = lecturerDao.getALL();
        ArrayList<LecturerDto> lecturerDtos = new ArrayList<>();
        if (lecturerEntities != null){
                for (LecturerEntity lecturerEntity : lecturerEntities){
                lecturerDtos.add(new LecturerDto(lecturerEntity.getLecturer_id(),
                        lecturerEntity.getName(),
                        lecturerEntity.getQualification(),
                        lecturerEntity.getDepartment(),
                        lecturerEntity.getEmail(),
                        lecturerEntity.getContact()));
            }
        }
        return lecturerDtos;
    }
}
