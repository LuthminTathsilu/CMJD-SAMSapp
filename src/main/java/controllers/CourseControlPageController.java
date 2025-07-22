package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class CourseControlPageController {
    public Button CourseControlbtn;
    public Button StudentControlbtn;
    public Button LectureControlbtn;
    public Button SubjectControlbtn;
    public Button AttendenceControlbtn;
    public Button signOutbtn;
    public Button Homebtn;

    public void signOut(ActionEvent actionEvent) {

    }

    public void CStudentM(ActionEvent actionEvent) {
    }

    public void CLectureM(ActionEvent actionEvent) {
    }

    public void CSubjectM(ActionEvent actionEvent) {
    }

    public void CManagementM(ActionEvent actionEvent) {
    }

    public void CHomeM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Dashboard.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage .setScene(scene);
        stage.show();
    }

    public void CourseSaveOnAction(ActionEvent actionEvent) {
    }

    public void CourseUpdateOnAction(ActionEvent actionEvent) {
    }

    public void CourseDeleteOnAction(ActionEvent actionEvent) {
    }
}
