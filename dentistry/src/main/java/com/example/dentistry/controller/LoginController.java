package com.example.dentistry.controller;

import com.example.dentistry.utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Button cancel_btn;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    public void cancelButton(ActionEvent e){
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connection = connectNow.getConnection();

        String username = usernameText.getText();
        String password = passwordText.getText();

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();


            if (username.isEmpty() && password.isEmpty()) {
                loginMessageLabel.setText("Please enter username and password!");
                loginMessageLabel.setStyle("-fx-text-fill: #C71D1D");
            } else if (username.isEmpty()) {
                loginMessageLabel.setText("Please enter username!");
                loginMessageLabel.setStyle("-fx-text-fill: #C71D1D");
            } else if (password.isEmpty()) {
                loginMessageLabel.setText("Please enter password!");
                loginMessageLabel.setStyle("-fx-text-fill: #C71D1D");
            } else if (resultSet.next()) {
                loginMessageLabel.setText("Login successfully!");
                loginMessageLabel.setStyle("-fx-text-fill: green");
            } else {
                loginMessageLabel.setText("Invalid username or password");
                loginMessageLabel.setStyle("-fx-text-fill: #C71D1D");
            }
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException e){
            e.getStackTrace();
            loginMessageLabel.setText("Error connecting to database!");
        }
    }
}
