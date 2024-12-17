package com.example.managementapp;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteUserController {

    @FXML
    private TextField usernameField, firstNameField, lastNameField, emailField, addressField,
            nidField, nationalityField, occupationField, creditorField, balanceField;

    @FXML
    private Button deleteButton;
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    public void initialize() {
        deleteButton.setOnAction(event -> deleteUser());
    }

    private void deleteUser() {
        String username = usernameField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String nid = nidField.getText();
        String nationality = nationalityField.getText();
        String occupation = occupationField.getText();
        String creditor = creditorField.getText();
        String balanceText = balanceField.getText();

        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                address.isEmpty() || nid.isEmpty() || nationality.isEmpty() || occupation.isEmpty() ||
                creditor.isEmpty() || balanceText.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        try {
            double balance = Double.parseDouble(balanceText);
            boolean isDeleted = DBUtilsn.deleteUserFromDatabase(username, firstName, lastName, email,
                    address, nid, nationality, occupation,
                    creditor, balance);

            if (isDeleted) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found or could not be deleted.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid balance format.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToGoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
