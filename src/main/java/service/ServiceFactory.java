package service;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        if(serviceFactory == null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getService(ServiceType type){
        switch (type) {
            case STUDENT:
                return null;
            case LECTURER:
                return null;
            case SUBJECT:
                return null;
            case ATTENDENCE:
                return null;
            case COURSE:
                return null;
            default:
                return null;
        }
    }

    public enum ServiceType{
        STUDENT, LECTURER, SUBJECT,ATTENDENCE,COURSE
    }
}
