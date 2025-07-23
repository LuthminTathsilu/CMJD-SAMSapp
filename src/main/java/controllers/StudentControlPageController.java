package controllers;

import db.DBConnection;
import dto.tm.StudentTm;
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
import service.Custom.StudentService;
import service.ServiceFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;


public class StudentControlPageController implements Initializable {
    public Button CourseControlbtn;
    public Button StudentControlbtn;
    public Button LectureControlbtn;
    public Button SubjectControlbtn;
    public Button AttendenceControlbtn;
    public Button Homebtn;
    public Button signOutbtn;
    public TextField txtStudentId;
    public TextField txtStudentName;
    public TextField txtStudentRegNumber;
    public TextField txtStudentEmail;
    public TextField txtStudentContact;
    public TextField txtStudentCourseId;
    public DatePicker dateDob;
    public ChoiceBox<String> choiceStudentGender;
    public AnchorPane ancStudent;
    public TableView<StudentTm> tblStudent;
    public TableColumn<StudentTm, String> studentIdcol;
    public TableColumn<StudentTm, String> studentNamecol;
    public TableColumn<StudentTm, String> studentRegNumbercol;
    public TableColumn<StudentTm, String> studentGendercol;
    public TableColumn<StudentTm, Date> studentDobcol;
    public TableColumn<StudentTm, String> studentEmailcol;
    public TableColumn<StudentTm, String> studentContactcol;
    public TableColumn<StudentTm, String> studentCourseIdcol;

    private Connection connection;
    private final StudentService studentService = (StudentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STUDENT);
    private ToggleGroup genderGroup;


    private ObservableList<StudentTm> addStudentsListD;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadGenderOptions();
        addStudentsShowListData();

        tblStudent.setOnMouseClicked(event -> onStudentTableClick());
    }

    private void loadGenderOptions() {
        choiceStudentGender.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
    }

    public ObservableList<StudentTm> addStudentsListData() {
        ObservableList<StudentTm> listStudents = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Student";

        try (PreparedStatement prepare = connection.prepareStatement(sql);
             ResultSet rst = prepare.executeQuery()) {

            while (rst.next()) {
                StudentTm studentTm = new StudentTm(
                        rst.getString("student_id"),
                        rst.getString("name"),
                        rst.getString("reg_number"),
                        rst.getString("gender"),
                        rst.getDate("dob"),
                        rst.getString("email"),
                        rst.getString("contact"),
                        rst.getString("course_id")
                );
                listStudents.add(studentTm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        studentIdcol.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        studentNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentRegNumbercol.setCellValueFactory(new PropertyValueFactory<>("reg_number"));
        studentGendercol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        studentDobcol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        studentEmailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentContactcol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        studentCourseIdcol.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        tblStudent.setItems(addStudentsListD);
    }

    public void onStudentTableClick() {
        StudentTm selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            txtStudentId.setText(selectedStudent.getStudent_id());
            txtStudentName.setText(selectedStudent.getName());
            txtStudentRegNumber.setText(selectedStudent.getReg_number());
            txtStudentEmail.setText(selectedStudent.getEmail());
            txtStudentContact.setText(selectedStudent.getContact());
            txtStudentCourseId.setText(selectedStudent.getCourse_id());

            if (selectedStudent.getDob() != null) {
                dateDob.setValue(LocalDate.parse(selectedStudent.getDob().toLocaleString()));
            }

            choiceStudentGender.setValue(selectedStudent.getGender());
        }
    }

    public void signOut(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Logout Confirmation");
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.OK) {
                signOutbtn.getScene().getWindow().hide();
                Parent load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
                new Stage() {{
                    setScene(new Scene(load));
                    show();
                }};
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stuCourseM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CourseControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void stuLectureM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LectureControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void stuSubjectM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/SubjectControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void stuAttendenceM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AttendanceControlPage.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void CHomeM(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Dashboard.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }


    public void StudentSearchOnAction(ActionEvent actionEvent) {
        String sql = "SELECT * FROM Student WHERE student_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, txtStudentId.getText());
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                txtStudentName.setText(rst.getString("name"));
                txtStudentRegNumber.setText(rst.getString("reg_number"));
                choiceStudentGender.setValue(rst.getString("gender"));
                dateDob.setValue(rst.getDate("dob").toLocalDate());
                txtStudentEmail.setText(rst.getString("email"));
                txtStudentContact.setText(rst.getString("contact"));
                txtStudentCourseId.setText(rst.getString("course_id"));
            } else {
                new Alert(Alert.AlertType.WARNING, "Student not found").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error searching student").show();
        }

    }
    public void StudentDeleteOnAction(ActionEvent actionEvent) {
        String sql = "DELETE FROM Student WHERE student_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, txtStudentId.getText());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully!").show();
                clearStudentFields();
                addStudentsShowListData(); // Refresh table
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error deleting student").show();
        }

    }
    public void StudentUpdateOnAction(ActionEvent actionEvent) {
        String sql = "UPDATE Student SET name=?, reg_number=?, gender=?, dob=?, email=?, contact=?, course_id=? WHERE student_id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, txtStudentName.getText());
            stmt.setString(2, txtStudentRegNumber.getText());
            stmt.setString(3, choiceStudentGender.getValue());
            stmt.setDate(4, Date.valueOf(dateDob.getValue()));
            stmt.setString(5, txtStudentEmail.getText());
            stmt.setString(6, txtStudentContact.getText());
            stmt.setString(7, txtStudentCourseId.getText());
            stmt.setString(8, txtStudentId.getText());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Student updated successfully!").show();
                addStudentsShowListData();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error updating student").show();
        }

    }
    public void StudentSaveOnAction(ActionEvent actionEvent) {
        String sql = "INSERT INTO Student (student_id, name, reg_number, gender, dob, email, contact, course_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, txtStudentId.getText());
            stmt.setString(2, txtStudentName.getText());
            stmt.setString(3, txtStudentRegNumber.getText());
            stmt.setString(4, choiceStudentGender.getValue());
            stmt.setDate(5, Date.valueOf(dateDob.getValue()));
            stmt.setString(6, txtStudentEmail.getText());
            stmt.setString(7, txtStudentContact.getText());
            stmt.setString(8, txtStudentCourseId.getText());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Student saved successfully!").show();
                addStudentsShowListData(); // Refresh table
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error saving student").show();
        }

    }
    private void clearStudentFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentRegNumber.clear();
        txtStudentEmail.clear();
        txtStudentContact.clear();
        txtStudentCourseId.clear();
        dateDob.setValue(null);
        choiceStudentGender.setValue(null);
    }
}
