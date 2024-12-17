package com.example.managementapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;
    @FXML
    private Label label_fav_channel;
    @FXML
    private Button Sonali_Bank;
//    @FXML
//    private Button Rupali_Bank;
//    @FXML
//    private Button Janata_Bank;
//    @FXML
//    private Button DBBL_Bank;

    private Stage stage;
    private Parent root;
    private Scene scene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DbUtils.changeScene(actionEvent, "sample.fxml", "Log in!", null, null);

            }
        });
    }
    public void setUserInformation(String username, String favChannel) {
        label_welcome.setText("Welcome, " + username + "!");
        label_fav_channel.setText("Your Favorite Bank is " + favChannel + ".");
    }
    public void switchToSonaliBank(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("JanataBankLogin.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRupaliBank(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("JanataBankLogin.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToJanataBank(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("JanataBankLogin.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDBBLBank(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("JanataBankLogin.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
