//package com.example.jsondesktoplearninglab;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class JanataBankLogin implements Initializable {
//
//    @FXML
//    private Button login_profile;
//
//    @FXML
//    private Label messageLabel;
//
//    @FXML
//    private PasswordField password_profile;
//
//    @FXML
//    private TextField username_profile;
//    @FXML
//    private Button sign_upmenu;
//
//    @FXML
//    void handleLogin(ActionEvent event) {
//
//    }
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        login_profile.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                    Databasepractice.loginuser_profile(event, username_profile.getText(), password_profile.getText());
//
//
//            }
//        });
//        sign_upmenu.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Databasepractice.changeScene(event, "RupaliBankSignUp.fxml", "Sign up!", null, null);
//                // Use Platform.runLater() to resize the stage after the scene is updated
////                Platform.runLater(new Runnable() {
////                    @Override
////                    public void run() {
////                        // Get the current stage (the window)
////                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////
////                        // Set the desired size (width, height)
////                        stage.setWidth(800);  // Set the width
////                        stage.setHeight(1000); // Set the height
////                    }
////                });
//            }
//        });
//    }
//
//
//
//}
package com.example.managementapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class JanataBankLogin implements Initializable {

    @FXML
    private Button login_profile;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField password_profile;

    @FXML
    private TextField username_profile;

    @FXML
    private Button sign_upmenu;

    @FXML
    private ChoiceBox<String> loginChoiceBox;

    @FXML
    void handleLogin(ActionEvent event) {
        String loginType = loginChoiceBox.getValue();

        if ("Admin".equals(loginType)) {
            if ("mubin".equals(username_profile.getText()) && "mubin123".equals(password_profile.getText())) {
                // Redirect to admin scene
                Databaseprofile.changeScene(event, "admin.fxml", "Admin Dashboard", null, null,null,null,null,null,null,null,null,null);
            } else {
                messageLabel.setText("Invalid Admin credentials");
            }
        } else if ("Client".equals(loginType)) {
            // Handle client login as usual
            Databaseprofile.loginuser_profile(event, username_profile.getText(), password_profile.getText());
        } else {
            messageLabel.setText("Please select login type");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the ChoiceBox with options for Admin and Client login
        loginChoiceBox.getItems().addAll("Admin", "Client");
        loginChoiceBox.setValue("Client");  // Default to Client login

        login_profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(event);
            }
        });

        sign_upmenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Databaseprofile.changeScene(event, "RupaliBankSignUp.fxml", "Sign up!", null, null,null,null,null,null,null,null,null,null);
            }
        });
    }
}

