module com.ijse.cmjd.samsapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ijse.cmjd.samsapp to javafx.fxml;
    exports com.ijse.cmjd.samsapp;
}