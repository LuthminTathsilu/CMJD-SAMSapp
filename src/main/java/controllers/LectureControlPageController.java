package controllers;

import db.DBConnection;
import dto.tm.LecturerTm;
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
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class LectureControlPageController implements Initializable {
    public Button CourseControlbtn;
    public Button LectureControlbtn;
    public Button SubjectControlbtn;
    public Button StudentControlbtn;
    public Button AttendenceControlbtn;
    public Button signOutbtn;
    public Button Homebtn;
    public AnchorPane ancLecturer;
    public TableView<LecturerTm> tblLecture;
    public TableColumn<LecturerTm, String> lectureIdcol;
    public TableColumn<LecturerTm, String> lectureNamecol;
    public TableColumn<LecturerTm, String> lectureQualificationcol;
    public TableColumn<LecturerTm, String> lectureDepartmentcol;
    public TableColumn<LecturerTm, String> lectureEmailcol;
    public TableColumn<LecturerTm, String> lectureContactcol;
    public TextField txtlectureId;
    public TextField txtlectureName;
    public TextField txtLEctureQualificaftion;
    public TextField txtLectureDepartment;
    public TextField txtLectureEmail;
    public TextField txtLectureContact;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadLecturerTable();
        setTableRowListener();
    }

    public void loadLecturerTable() {
        ObservableList<LecturerTm> lecturerList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Lecturer";

        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                lecturerList.add(new LecturerTm(
                        rs.getString("lecturer_id"),
                        rs.getString("name"),
                        rs.getString("qualification"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("contact")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        lectureIdcol.setCellValueFactory(new PropertyValueFactory<>("lecturer_id"));
        lectureNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lectureQualificationcol.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        lectureDepartmentcol.setCellValueFactory(new PropertyValueFactory<>("department"));
        lectureEmailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        lectureContactcol.setCellValueFactory(new PropertyValueFactory<>("contact"));

        tblLecture.setItems(lecturerList);
    }

    private void setTableRowListener() {
        tblLecture.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtlectureId.setText(newVal.getLecturer_id());
                txtlectureName.setText(newVal.getName());
                txtLEctureQualificaftion.setText(newVal.getQualification());
                txtLectureDepartment.setText(newVal.getDepartment());
                txtLectureEmail.setText(newVal.getEmail());
                txtLectureContact.setText(newVal.getContact());
            }
        });
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                signOutbtn.getScene().getWindow().hide();
                Parent load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(load));
                stage.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void LCourseM(ActionEvent actionEvent) throws IOException {
        loadPage("/view/CoursecontrolPage.fxml");
    }

    public void LStudentM(ActionEvent actionEvent) throws IOException {
        loadPage("/view/StudentControlPage.fxml");
    }

    public void LSubjectM(ActionEvent actionEvent) throws IOException {
        loadPage("/view/SubjectControlPage.fxml");
    }

    public void LAttendenceM(ActionEvent actionEvent) throws IOException {
        loadPage("/view/AttendenceControlPage.fxml");
    }

    public void LHomeM(ActionEvent actionEvent) throws IOException {
        loadPage("/view/Dashboard.fxml");
    }

    private void loadPage(String path) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void LectureSearchOnAction(ActionEvent actionEvent) {
        String id = txtlectureId.getText();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM Lecturer WHERE lecturer_id = ?"
            );
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtlectureName.setText(rs.getString("name"));
                txtLEctureQualificaftion.setText(rs.getString("qualification"));
                txtLectureDepartment.setText(rs.getString("department"));
                txtLectureEmail.setText(rs.getString("email"));
                txtLectureContact.setText(rs.getString("contact"));
            } else {
                new Alert(Alert.AlertType.WARNING, "Lecturer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void LectureDeleteOnAction(ActionEvent actionEvent) {
        String id = txtlectureId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this lecturer?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                PreparedStatement ps = connection.prepareStatement(
                        "DELETE FROM Lecturer WHERE lecturer_id = ?"
                );
                ps.setString(1, id);

                if (ps.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Lecturer Deleted Successfully!").show();
                    loadLecturerTable();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Delete failed. Lecturer ID not found.").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    public void LectureUpdateOnAction(ActionEvent actionEvent) {
        String id = txtlectureId.getText();
        String name = txtlectureName.getText();
        String qualification = txtLEctureQualificaftion.getText();
        String department = txtLectureDepartment.getText();
        String email = txtLectureEmail.getText();
        String contact = txtLectureContact.getText();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Lecturer SET name = ?, qualification = ?, department = ?, email = ?, contact = ? WHERE lecturer_id = ?"
            );
            ps.setString(1, name);
            ps.setString(2, qualification);
            ps.setString(3, department);
            ps.setString(4, email);
            ps.setString(5, contact);
            ps.setString(6, id);

            if (ps.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Lecturer Updated Successfully!").show();
                loadLecturerTable();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Update failed. Lecturer ID not found.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void LectureSaveOnAction(ActionEvent actionEvent) {
        String id = txtlectureId.getText();
        String name = txtlectureName.getText();
        String qualification = txtLEctureQualificaftion.getText();
        String department = txtLectureDepartment.getText();
        String email = txtLectureEmail.getText();
        String contact = txtLectureContact.getText();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Lecturer (lecturer_id, name, qualification, department, email, contact) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, qualification);
            ps.setString(4, department);
            ps.setString(5, email);
            ps.setString(6, contact);

            if (ps.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Lecturer Saved Successfully!").show();
                loadLecturerTable();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }
    private void clearFields() {
        txtlectureId.clear();
        txtlectureName.clear();
        txtLEctureQualificaftion.clear();
        txtLectureDepartment.clear();
        txtLectureEmail.clear();
        txtLectureContact.clear();
    }

}
