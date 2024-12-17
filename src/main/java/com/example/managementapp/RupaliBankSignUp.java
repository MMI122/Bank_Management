package com.example.managementapp;



import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class RupaliBankSignUp implements Initializable {
    @FXML
    private TextField First_Name;

    @FXML
    private TextField Last_Name;

    @FXML
    private TextField address_profile;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TextField creditor_profile;

    @FXML
    private TextField email;

    @FXML
    private ImageView leftSideImage;

    @FXML
    private Button login_menu;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField nationality_profile;

    @FXML
    private TextField nid_profile;

    @FXML
    private TextField occupation_profile;

    @FXML
    private PasswordField password_profile;

    @FXML
    private StackPane root;

    @FXML
    private Button signup_profile;

    @FXML
    private TextField username_profile;

//    @FXML
//    private TextField First_Name;
//
//    @FXML
//    private TextField addressField;
//
//    @FXML
//    private TextField lastNameField;
//
//    @FXML
//    private Label messageLabel;
//
//    @FXML
//    private PasswordField password_profile;
//
//    @FXML
//    private TextField username_profile;
//
//    @FXML
//    private Button signup_profile;
//
//    @FXML
//    private Button login_menu;
//
//    @FXML
//    private StackPane root; // StackPane for animation

    // Fade-in and Slide-in Animations
    private void applyAnimations() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), signup_profile);
        slideIn.setFromX(-signup_profile.getLayoutBounds().getWidth()); // Slide in from the left
        slideIn.setToX(0);
        slideIn.play();
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        if (!username_profile.getText().trim().isEmpty() && !First_Name.getText().trim().isEmpty() &&  !Last_Name.getText().trim().isEmpty() && !address_profile.getText().trim().isEmpty() && !creditor_profile.getText().trim().isEmpty() && !email.getText().trim().isEmpty() && !nationality_profile.getText().trim().isEmpty() && !nid_profile.getText().trim().isEmpty() && !occupation_profile.getText().trim().isEmpty() && !password_profile.getText().trim().isEmpty()) {

            Databaseprofile.signupuser_profile(event, username_profile.getText(), First_Name.getText(), Last_Name.getText(),email.getText(),address_profile.getText(),nid_profile.getText(),nationality_profile.getText(),occupation_profile.getText(),password_profile.getText(),creditor_profile.getText());
        } else {
            System.out.println("Please fill in all the fields");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all the fields to sign up");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyAnimations();

        signup_profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSignUp(event);
            }
        });

        login_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Databaseprofile.changeScene(event, "JanataBankLogin.fxml", "Log in!", null, null,null,null,null,null,null,null,null,null);
            }
        });
    }
}
