package dao.Custom.impl;

import dao.Custom.AttendenceDao;
import db.DBConnection;
import entity.AttendenceEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class AttendenceDaoImpl implements AttendenceDao {

    @Override
    public boolean save(AttendenceEntity attendenceEntity) throws Exception {
        String sql = "INSERT INTO attendence (id, student_id, class_id, status, marked_time) VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, attendenceEntity.getId());
        ps.setString(2, attendenceEntity.getStudentId());
        ps.setString(3, attendenceEntity.getClassId());
        ps.setString(4, attendenceEntity.getStatus());
        ps.setString(5, attendenceEntity.getMarkedTime());

        return ps.executeUpdate() > 0;
    }


    @Override
    public boolean update(AttendenceEntity attendenceEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public AttendenceEntity search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<AttendenceEntity> getALL() throws Exception {
        return null;
    }
}
