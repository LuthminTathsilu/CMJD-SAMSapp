package service.Custom.impl;

import dto.LecturerDto;
import dto.StudentDto;
import entity.LecturerEntity;
import entity.StudentEntity;
import service.Custom.LecturerService;

import java.util.ArrayList;

public class LecturerServiceImpl implements LecturerService {

    @Override
    public String saveLecturer(LecturerDto lecturerDto) throws Exception {
        LecturerEntity lecturerEntity = new LecturerEntity(
                lecturerDto.getLecturer_id(),
                lecturerDto.getName(),
                lecturerDto.getQualification(),
                lecturerDto.getDepartment(),
                lecturerDto.getEmail(),
                lecturerDto.getContact());

        boolean isSaved = true;
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

        boolean isUpdated = true;
        return isUpdated ? "Succes" : "fail";
    }

    @Override
    public String deleteLecturer(String lectureId) throws Exception {
        boolean isDeleted = true;
        return isDeleted ? "Succes" : "Fail";
    }

    @Override
    public LecturerDto searchLecturer(String CourseId) throws Exception {
        LecturerEntity lecturerEntity = null;
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
        ArrayList<LecturerEntity> lecturerEntities = null;
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
