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

    }

    public void LectureDeleteOnAction(ActionEvent actionEvent) {

    }

    public void LectureUpdateOnAction(ActionEvent actionEvent) {

    }

    public void LectureSaveOnAction(ActionEvent actionEvent) {

    }
}
