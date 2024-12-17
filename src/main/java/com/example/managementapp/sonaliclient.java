package com.example.managementapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

public class sonaliclient {
//    @FXML
//    private TextField First_Name;
//
//    @FXML
//    private TextField Last_Name;
//
//    @FXML
//    private TextField address_profile;
//
//    @FXML
//    private TextField creditor_profile;
//
//    @FXML
//    private TextField email;
//
//    @FXML
//    private TextField nationality_profile;
//
//    @FXML
//    private TextField nid_profile;
//
//    @FXML
//    private TextField occupation_profile;
//
//    @FXML
//    private TextField password_profile;
//
//    @FXML
//    private TextField username_profile;
//
//    @FXML
//    private VBox sidebar;
//
//    @FXML
//    private VBox transactionControls;
//
//    @FXML
//    private Label balanceLabel;
//
//    @FXML
//    private TextField amountField;
//
//    @FXML
//    private Label messageLabel;
//
//    @FXML
//    private Button button_main;
//
//    @FXML
//    private Button button_sidebar;
//
//    @FXML
//    private AnchorPane mainContent;
//
//    @FXML
//    private AnchorPane ProfilePane;
@FXML
private TextField First_Name;

    @FXML
    private TextField Last_Name;

    @FXML
    private AnchorPane ProfilePane;

    @FXML
    private TextField address_profile;

    @FXML
    private TextField amountField;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Label balanceLabel;

    @FXML
    private ComboBox<String> baseCurrencyCombo;

    @FXML
    private Button button_main;

    @FXML
    private Button button_sidebar;

    @FXML
    private TextField creditor_profile;

    @FXML
    private TextField email;

    @FXML
    private Label exchangeRateLabel;

    @FXML
    private VBox jsonpracticekori;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField nationality_profile;

    @FXML
    private TextField nid_profile;

    @FXML
    private TextField occupation_profile;

    @FXML
    private TextField password_profile;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox sidebar;

    @FXML
    private ComboBox<String> targetCurrencyCombo;

    @FXML
    private VBox transactionControls;

    @FXML
    private TextField username_profile;

    private boolean isSidebarVisible = true;
    private boolean transactionControlsVisible = false;
    private boolean isjsonpracticekoriVisible = false;
    private boolean profileVisible = false;
    private String loggedInUsername;
    private Stage stage;
    private Parent root;
    private Scene scene;


    @FXML
    private void initialize() {
        sidebar.setVisible(isSidebarVisible);
        jsonpracticekori.setVisible(isjsonpracticekoriVisible);
        button_main.setVisible(false);
        button_sidebar.setVisible(true);
        ProfilePane.setVisible(false);
        baseCurrencyCombo.getItems().addAll("USD", "EUR", "GBP", "BDT", "JPY");
        targetCurrencyCombo.getItems().addAll("USD", "EUR", "GBP", "BDT", "JPY");

        // Set default selections
        baseCurrencyCombo.setValue("USD");
        targetCurrencyCombo.setValue("BDT");
    }

    public void setUserInformation(String username, String firstName, String lastname,String ee,String address,String nid,String nationality,String occupation,String password,String creditor,double balance) {
        this.loggedInUsername = username;
        this.username_profile.setText(username);
        this.First_Name.setText(firstName);
        this.Last_Name.setText(lastname);
        this.email.setText(ee);
        this.address_profile.setText(address);
        this.nid_profile.setText(nid);
        this.nationality_profile.setText(nationality);
        this.occupation_profile.setText(occupation);
        this.password_profile.setText(password);
        this.creditor_profile.setText(creditor);
        this.balanceLabel.setText("Balance: " + balance);

        // Update balance on the UI for the logged-in user
        updateBalanceLabel();
    }

    public void switchToFirstScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("JanataBankLogin.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toggleSidebar() {
        isSidebarVisible = !isSidebarVisible;
        sidebar.setVisible(isSidebarVisible);
        button_main.setVisible(!isSidebarVisible);
        button_sidebar.setVisible(isSidebarVisible);
    }

    @FXML
    private void showTransactionControls() {
        transactionControlsVisible = !transactionControlsVisible;
        transactionControls.setVisible(transactionControlsVisible);
    }
    @FXML
    private void showExchangeRate(){
        isjsonpracticekoriVisible = !isjsonpracticekoriVisible;
        jsonpracticekori.setVisible(isjsonpracticekoriVisible);
    }


    @FXML
    private void ShowProfile() {
        profileVisible = !profileVisible;
        ProfilePane.setVisible(profileVisible);
    }

    @FXML
    private void handleCredit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                messageLabel.setText("Amount must be greater than zero.");
                return;
            }
            updateBalance(amount, true);
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number.");
        }
    }
    @FXML
    public void fetchExchangeRate() {
        String baseCurrency = baseCurrencyCombo.getValue();
        String targetCurrency = targetCurrencyCombo.getValue();

        if (baseCurrency == null || targetCurrency == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select both base and target currencies.");
            return;
        }

        try {
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonResponse.toString());
            double rate = jsonObject.getJSONObject("rates").getDouble(targetCurrency);

            exchangeRateLabel.setText("Exchange Rate: " + rate);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch exchange rate.");
        }
    }

    @FXML
    private void handleDebit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                messageLabel.setText("Amount must be greater than zero.");
                return;
            }
            updateBalance(amount, false);
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number.");
        }
    }

    private void updateBalance(double amount, boolean isCredit) {
        String query = "UPDATE dataforprofile SET balance = balance + ? WHERE username_profile = ?";
        if (!isCredit) {
            query = "UPDATE dataforprofile SET balance = balance - ? WHERE username_profile = ?";
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/profilework", "root", "");//password silo ekhane7
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, loggedInUsername);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                messageLabel.setText(isCredit ? "Amount credited successfully!" : "Amount debited successfully!");
                updateBalanceLabel();
            } else {
                messageLabel.setText("Failed to update balance. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("An error occurred while updating the balance.");
        }
    }

    private void updateBalanceLabel() {
        String query = "SELECT balance FROM dataforprofile WHERE username_profile = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/profilework", "root", "");//password silo ekhane8
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, loggedInUsername);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                balanceLabel.setText("Balance: " + balance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            balanceLabel.setText("Balance: Error fetching balance");
        }
    }
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }


}
