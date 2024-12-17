package com.example.managementapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//public void initialize(URL location, ResourceBundle resources) {
//    // TODO
//    button_logout.setOnAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent actionEvent) {
//            DbUtils.changeScene(actionEvent, "sample.fxml", "Log in!", null, null);
//
//        }
//    });
//}

public class MainController implements Initializable {
    @FXML
    private Button button_login;
    @FXML
    private Button button_sign_up;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    //I added this python lines here
    @FXML
    protected void onPythonLoginButtonClick() {
        // This method will run the Python script when the "Run Python Login" button is clicked.
        try {
            // Path to the Python script
            String scriptPath = "src/main/java/python_scripts/Face_recognition_based_attendance_system-master/main.py";
            // Adjust this path
            //src/main/java/python_scripts/Face_recognition_based_attendance_system-master/main.py

            // Create ProcessBuilder to run the Python script
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);
            processBuilder.start();  // Start the Python script
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DbUtils.logInUser(event, tf_username.getText(), tf_password.getText());
            }
        });
        button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DbUtils.changeScene(event, "sign-up.fxml", "Sign up!", null, null);
            }
        });

        // TODO
    }
//    public void switchToSignedUp(ActionEvent event) {
//        DbUtils.changeScene(event, "sign-up.fxml", "Sign up!", null, null);
//    }
}
