package controllers;

import db.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPageController {
    public AnchorPane ancMain;
    public TextField txtusername;
    public PasswordField txtpassword;
    public Button loginbtn;

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet rst;


    public void loginAdmin() throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ? and password =?";
        connection = DBConnection.getInstance().getConnection();
        try {
            Alert alert;
            statement = connection.prepareStatement(sql);
            statement.setString(1, txtusername.getText());
            statement.setString(2, txtpassword.getText());
            rst = statement.executeQuery();

            if (txtusername.getText().isEmpty() || txtpassword.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setContentText("Please Fill all blank Fields");
                alert.showAndWait();
            }else{
                if (rst.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setContentText("Succesfully login!");
                    alert.showAndWait();

                    loginbtn.getScene().getWindow().hide();
                    Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Dashboard.fxml")));

                    Stage stage = new Stage();
                    Scene scene = new Scene(load);
                    stage .setScene(scene);
                    stage.show();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setContentText("Wrong Username or Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {e.printStackTrace();}

    }
}

