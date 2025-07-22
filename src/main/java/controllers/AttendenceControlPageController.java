package controllers;

import db.DBConnection;
import dto.tm.AttendenceTm;
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

public class AttendenceControlPageController implements Initializable {
    public Button CourseControlbtn;
    public Button StudentControlbtn;
    public Button LectureControlbtn;
    public Button SubjectControlbtn;
    public Button AttendenceControlbtn;
    public Button signOutbtn;
    public Button Homebtn;
    public AnchorPane ancAttendence;

    public TextField txtAttendenceStudentId;
    public TextField txtAttendenceClassId;
    public TextField txtAttendenceStatus;
    public TextField txtAttendenceMarkedTime;
    public TableView<AttendenceTm> tblAttendence;
    public TableColumn<AttendenceTm, String> attendenceIdcol;
    public TableColumn<AttendenceTm, String> attendenceStudentIdcol;
    public TableColumn<AttendenceTm, String> attendenceClassIdcol;
    public TableColumn<AttendenceTm, String> attendenceStatuscol;
    public TableColumn<AttendenceTm, String> attendenceMarkedTimecol;
    public TextField txtAttendanceId;


    Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        attendenceIdcol.setCellValueFactory(new PropertyValueFactory<>("attendenceid"));
        attendenceStudentIdcol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        attendenceClassIdcol.setCellValueFactory(new PropertyValueFactory<>("classId"));
        attendenceStatuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        attendenceMarkedTimecol.setCellValueFactory(new PropertyValueFactory<>("markedTime"));

        loadAttendences();

        tblAttendence.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtAttendanceId.setText(newVal.getId());
                txtAttendenceStudentId.setText(newVal.getStudentId());
                txtAttendenceClassId.setText(newVal.getClassId());
                txtAttendenceStatus.setText(newVal.getStatus());
                txtAttendenceMarkedTime.setText(newVal.getMarkedTime());
            }
        });
    }

    private void loadAttendences() {
        ObservableList<AttendenceTm> attendences = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Attendance");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                attendences.add(new AttendenceTm(
                        rs.getString("attendance_id"),
                        rs.getString("student_id"),
                        rs.getString("class_id"),
                        rs.getString("status"),
                        rs.getString("marked_time")
                ));
            }
            tblAttendence.setItems(attendences);
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
                Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginPage.fxml")));
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ACourseM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CoursecontrolPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void AStudentM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StudentControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void ALectureM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LectureControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void ASubjectM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/SubjectControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void AHomeM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Dashboard.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void AttendenceSaveOnAction(ActionEvent actionEvent) {
        String id = txtAttendanceId.getText();
        String studentId = txtAttendenceStudentId.getText();
        String classId = txtAttendenceClassId.getText();
        String status = txtAttendenceStatus.getText();
        String markedTime = txtAttendenceMarkedTime.getText();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Attendance (attendance_id, student_id, class_id, status, marked_time) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setString(1, id);
            ps.setString(2, studentId);
            ps.setString(3, classId);
            ps.setString(4, status);
            ps.setString(5, markedTime);

            if (ps.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Attendance Saved Successfully!").show();
                loadAttendences();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void AttendenceSearchOnAction(ActionEvent actionEvent) {
        String id = txtAttendanceId.getText();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM Attendance WHERE attendance_id = ?"
            );
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtAttendenceStudentId.setText(rs.getString("student_id"));
                txtAttendenceClassId.setText(rs.getString("class_id"));
                txtAttendenceStatus.setText(rs.getString("status"));
                txtAttendenceMarkedTime.setText(rs.getString("marked_time"));
            } else {
                new Alert(Alert.AlertType.WARNING, "No record found with this ID.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void AttendenceUpdateOnAction(ActionEvent actionEvent) {
        String id = txtAttendanceId.getText();
        String studentId = txtAttendenceStudentId.getText();
        String classId = txtAttendenceClassId.getText();
        String status = txtAttendenceStatus.getText();
        String markedTime = txtAttendenceMarkedTime.getText();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Attendance SET student_id = ?, class_id = ?, status = ?, marked_time = ? WHERE attendance_id = ?"
            );
            ps.setString(1, studentId);
            ps.setString(2, classId);
            ps.setString(3, status);
            ps.setString(4, markedTime);
            ps.setString(5, id);

            if (ps.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Attendance Updated Successfully!").show();
                loadAttendences();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Update failed. ID not found.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void clearFields() {
        txtAttendanceId.clear();
        txtAttendenceStudentId.clear();
        txtAttendenceClassId.clear();
        txtAttendenceStatus.clear();
        txtAttendenceMarkedTime.clear();
    }

    public void stuAttendenceM(ActionEvent actionEvent){
    }
}

