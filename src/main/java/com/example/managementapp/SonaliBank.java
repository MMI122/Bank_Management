package com.example.managementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SonaliBank {

    @FXML
    private ChoiceBox<?> acc_selector;

    @FXML
    private Label error_lbl;

    @FXML
    private Button login_btn;

    @FXML
    private TextField password_fld;

    @FXML
    private TextField payee_address_fld;

    @FXML
    private Label payee_address_lbl;
    private Stage stage;
    private Parent root;
    private Scene scene;
    public void switchToSonaliBankClientMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SonaliBankClientMenu.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
