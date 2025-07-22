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

public class DashboardController {
    public Button CourseControlbtn;
    public Button StudentControlbtn;
    public Button LectureControlbtn;
    public Button SubjectControlbtn;
    public Button AttendenceControlbtn;
    public Button signOutbtn;

    public void CourseControl(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CoursecontrolPage.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage .setScene(scene);
        stage.show();
    }

    public void StudentControl(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StudentControlPage.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage .setScene(scene);
        stage.show();
    }

    public void LectureControl(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LectureControlPage.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage .setScene(scene);
        stage.show();
    }

    public void SubjectControl(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/SubjectControlPage.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage .setScene(scene);
        stage.show();
    }

    public void AttendenceControl(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AttendenceControlPage.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(load);
        stage .setScene(scene);
        stage.show();
    }

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
}
