module com.ijse.cmjd.samsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens com.ijse.cmjd.samsapp to javafx.fxml;
    exports com.ijse.cmjd.samsapp;
    exports controllers;
    opens controllers to javafx.fxml;
    opens dto.tm to javafx.base;
    exports dto.tm;
}