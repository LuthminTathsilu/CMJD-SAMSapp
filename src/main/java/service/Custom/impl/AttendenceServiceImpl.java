package service.Custom.impl;

import dao.Custom.AttendenceDao;
import dao.Custom.impl.AttendenceDaoImpl;
import db.DBConnection;
import dto.AttendenceDto;
import entity.AttendenceEntity;
import service.Custom.AttendenceService;

import java.sql.Connection;
import java.util.ArrayList;

public class AttendenceServiceImpl implements AttendenceService {

    AttendenceDao attendenceDao = new AttendenceDaoImpl();

    @Override
    public String saveAttendence(AttendenceDto attendenceDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            AttendenceEntity attendenceEntity = new AttendenceEntity(
                    attendenceDto.getId(),
                    attendenceDto.getStudentId(),
                    attendenceDto.getClassId(),
                    attendenceDto.getStatus(),
                    attendenceDto.getMarkedTime()
            );

            boolean isSaved = attendenceDao.save(attendenceEntity);

            if (isSaved) {
                connection.commit();
                return "Success";
            } else {
                connection.rollback();
                return "Fail";
            }

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return "Error";
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String updateAttendence(AttendenceDto attendenceDto) throws Exception {

        return null;
    }

    @Override
    public AttendenceDto searchAttendence(String attendenceId) throws Exception {
        return null;
    }

    @Override
    public ArrayList<AttendenceDto> getAllAttendence() throws Exception {
        return null;
    }
}
