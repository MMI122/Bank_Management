package com.example.managementapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignedUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_log_in;
    @FXML
    private RadioButton rb_wittcode;
    @FXML
    private RadioButton rb_someone_else;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup=new ToggleGroup();
        rb_wittcode.setToggleGroup(toggleGroup);
        rb_someone_else.setToggleGroup(toggleGroup);
        rb_wittcode.setSelected(true);
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName=((RadioButton)toggleGroup.getSelectedToggle()).getText();
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                    DbUtils.signUpUser(event, tf_username.getText(), tf_password.getText(), toggleName);
                }else{
                    System.out.println("Please fill in all the fields");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all the fields to sign up");
                    alert.show();
                }
            }
        });
        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DbUtils.changeScene(event, "sample.fxml", "Log in!", null, null);
            }
        });

        // TODO
    }

}
