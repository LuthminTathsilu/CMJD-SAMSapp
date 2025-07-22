package controllers;

import db.DBConnection;
import dto.tm.SubjectTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class SubjectControlPageController implements Initializable {
    public Button CourseControlbtn;
    public Button StudentControlbtn;
    public Button LectureControlbtn;
    public Button SubjectControlbtn;
    public Button AttendenceControlbtn;
    public Button signOutbtn;
    public Button Homebtn;
    public AnchorPane ancSubject;
    public TextField txtSubjectId;
    public TextField txtSubjectName;
    public TextField txtSubjectCreditHours;
    public TextField txtSubjectCourseId;
    public TableView<SubjectTm> tblSubject;
    public TableColumn<SubjectTm, String> subjectIdcol;
    public TableColumn<SubjectTm, String> subjectNamecol;
    public TableColumn<SubjectTm, String> subjectCreditHourscol;
    public TableColumn<SubjectTm, String> subjectCourseIdcol;

    Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        subjectIdcol.setCellValueFactory(new PropertyValueFactory<>("subjectId"));
        subjectNamecol.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        subjectCreditHourscol.setCellValueFactory(new PropertyValueFactory<>("creditHours"));
        subjectCourseIdcol.setCellValueFactory(new PropertyValueFactory<>("courseId"));

        loadSubjects();

        tblSubject.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtSubjectId.setText(newVal.getSubjectId());
                txtSubjectName.setText(newVal.getSubjectName());
                txtSubjectCreditHours.setText(newVal.getCreditHours());
                txtSubjectCourseId.setText(newVal.getCourseId());
            }
        });
    }

    private void loadSubjects() {
        ObservableList<SubjectTm> subjects = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Subject");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new SubjectTm(
                        rs.getString("subject_id"),
                        rs.getString("name"),
                        rs.getString("credit_hours"),
                        rs.getString("course_id")
                ));
            }
            tblSubject.setItems(subjects);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                signOutbtn.getScene().getWindow().hide();
                Parent load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void subCourseM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CoursecontrolPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void subStudentM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StudentControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void subLectureM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LectureControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void subAttendenceM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AttendenceControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void subHomeM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Dashboard.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void SubjectSaveOnAction(ActionEvent actionEvent) {
    }

    public void SubjectUpdateOnAction(ActionEvent actionEvent) {
    }

    public void SubjectDeleteOnAction(ActionEvent actionEvent) {
    }

    public void stuSubjectM(ActionEvent actionEvent) {
    }
}
