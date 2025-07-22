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
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Massage");
            alert.setContentText("Are you sure you want to logout? ");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                signOutbtn.getScene().getWindow().hide();
                Parent load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage .setScene(scene);
                stage.show();
            }else return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
}
