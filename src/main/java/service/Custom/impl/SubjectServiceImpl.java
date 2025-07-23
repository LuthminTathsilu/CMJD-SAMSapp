package service.Custom.impl;

import dto.StudentDto;
import dto.SubjectDto;
import entity.StudentEntity;
import entity.SubjectEntity;
import service.Custom.SubjectService;

import java.util.ArrayList;

public class SubjectServiceImpl implements SubjectService {

    @Override
    public String saveSubject(SubjectDto subjectDto) throws Exception {
        SubjectEntity subjectEntity = new SubjectEntity(
                subjectDto.getSubjectId(),
                subjectDto.getSubjectName(),
                subjectDto.getCreditHours(),
                subjectDto.getCourseId());

        boolean isSaved = true;
        return isSaved ? "Succes" : "fail";
    }

    @Override
    public String updateSubject(SubjectDto subjectDto) throws Exception {
        SubjectEntity subjectEntity = new SubjectEntity(
                subjectDto.getSubjectId(),
                subjectDto.getSubjectName(),
                subjectDto.getCreditHours(),
                subjectDto.getCourseId());

        boolean isUpdated = true;
        return isUpdated ? "Succes" : "fail";
    }

    @Override
    public String deleteSubject(String SubjectId) throws Exception {
        boolean isDeleted = true;
        return isDeleted ? "Succes" : "Fail";
    }

    @Override
    public SubjectDto searchSubject(String SubjectId) throws Exception {
        SubjectEntity subjectEntity = null;
        if(subjectEntity != null){
            return new SubjectDto(
                    subjectEntity.getSubjectId(),
                    subjectEntity.getSubjectName(),
                    subjectEntity.getCreditHours(),
                    subjectEntity.getCourseId());
        }
        return null;
    }

    @Override
    public ArrayList<SubjectDto> getAllSubject() throws Exception {
        ArrayList<SubjectEntity> subjectEntities = null;
        ArrayList<SubjectDto> subjectDtos = new ArrayList<>();
        if (subjectEntities != null){
            for (SubjectEntity subjectEntity : subjectEntities){
                subjectDtos.add(new SubjectDto( subjectEntity.getSubjectId(),
                        subjectEntity.getSubjectName(),
                        subjectEntity.getCreditHours(),
                        subjectEntity.getCourseId()));
            }
        }
        return subjectDtos;
    }
}
