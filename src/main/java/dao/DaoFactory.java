package dao;

import dao.Custom.impl.StudentDaoImpl;

import java.lang.classfile.instruction.SwitchCase;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        if (daoFactory==null){
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
    public SuperDao getDao(DaoTypes type){
        switch (type){
            case STUDENT:
             return new StudentDaoImpl();
            case COURSE:
                return null;
            case ATTENDENCE:
                return null;
            case CLASS:
                return null;
            case LECTURER:
                return null;
            case USER:
                return null;
            case CLASS_LECTURER:
                return null;
            case SUBJECT:
                return null;
            default:
                return null;
        }

    }
    public enum DaoTypes{
        STUDENT, COURSE,ATTENDENCE,CLASS,SUBJECT,LECTURER,USER,CLASS_LECTURER

    }
}
