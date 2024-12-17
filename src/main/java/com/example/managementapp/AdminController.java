package com.example.managementapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @FXML
    private Button deleteUserButton;


    @FXML
    private Button informationButton;

    @FXML
    private TableView<UserData> dataTableView;

    @FXML
    private TableColumn<UserData, String> usernameColumn;
    @FXML
    private TableColumn<UserData, String> firstNameColumn;
    @FXML
    private TableColumn<UserData, String> lastNameColumn;
    @FXML
    private TableColumn<UserData, String> emailColumn;
    @FXML
    private TableColumn<UserData, String> addressColumn;
    @FXML
    private TableColumn<UserData, String> nidColumn;
    @FXML
    private TableColumn<UserData, String> nationalityColumn;
    @FXML
    private TableColumn<UserData, String> occupationColumn;
    @FXML
    private TableColumn<UserData, String> creditorColumn;
    @FXML
    private TableColumn<UserData, Double> balanceColumn;

    private final ObservableList<UserData> userDataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        //I am adding it for deleting user
        deleteUserButton.setOnAction(event -> openDeleteUserScene());
        // Set up TableView columns
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        nidColumn.setCellValueFactory(new PropertyValueFactory<>("nid"));
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        occupationColumn.setCellValueFactory(new PropertyValueFactory<>("occupation"));
        creditorColumn.setCellValueFactory(new PropertyValueFactory<>("creditor"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        // Set the TableView's data source
        dataTableView.setItems(userDataList);

        // Button action
        informationButton.setOnAction(event -> fetchAndDisplayData());
    }
    //I am adding it for deleting user
    private void openDeleteUserScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteUser.fxml"));
            Parent deleteUserRoot = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Delete User");
            stage.setScene(new Scene(deleteUserRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchAndDisplayData() {
        try {
            // Fetch data from database
            JsonArray jsonArray = DBUtilsn.fetchDataFromDatabase();

            // Clear existing data in the TableView
            userDataList.clear();

            // Populate TableView with data from the database
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                UserData userData = new UserData(
                        jsonObject.get("username").getAsString(),
                        jsonObject.get("first_name").getAsString(),
                        jsonObject.get("last_name").getAsString(),
                        jsonObject.get("email").getAsString(),
                        jsonObject.get("address").getAsString(),
                        jsonObject.get("nid").getAsString(),
                        jsonObject.get("nationality").getAsString(),
                        jsonObject.get("occupation").getAsString(),
                        jsonObject.get("creditor").getAsString(),
                        jsonObject.get("balance").getAsDouble()
                );
                userDataList.add(userData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
