package com.example.managementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SonaliBankClientMenuConroller {
    @FXML
    private VBox sidebar;
    @FXML
    private VBox transactionControls;
    @FXML
    private Label balanceLabel;
    @FXML
    private TextField amountField;
    @FXML
    private Label messageLabel;

//    private int accountId = 1; // Example account ID, replace with actual logic to get the logged-in user's account ID
//
//    private Connection connect() {
//        // MySQL connection string
//        String url = "jdbc:mysql://localhost:3306/BankDB";
//        String user = "root";
//        String password = "password";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//    @FXML
//    private void initialize() {
//        updateBalance();
//    }

    @FXML
    private void toggleSidebar() {
        sidebar.setVisible(!sidebar.isVisible());
    }

    @FXML
    private void showTransactionControls() {
        transactionControls.setVisible(true);
        //updateBalance();
    }

    public void handleCredit(ActionEvent event) {
    }

    public void handleDebit(ActionEvent event) {
    }

//    @FXML
//    private void handleCredit() {
//        double amount = Double.parseDouble(amountField.getText());
//        updateBalance(amount, true);
//    }
//
//    @FXML
//    private void handleDebit() {
//        double amount = Double.parseDouble(amountField.getText());
//        updateBalance(amount, false);
//    }
//
//    private void updateBalance() {
//        String query = "SELECT balance FROM Accounts WHERE account_id = ?";
//        try (Connection conn = connect();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setInt(1, accountId);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                double balance = rs.getDouble("balance");
//                balanceLabel.setText(String.valueOf(balance));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateBalance(double amount, boolean isCredit) {
//        String query = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
//        if (!isCredit) {
//            query = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
//        }
//        try (Connection conn = connect();
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setDouble(1, amount);
//            pstmt.setInt(2, accountId);
//            int rowsAffected = pstmt.executeUpdate();
//            if (rowsAffected > 0) {
//                messageLabel.setText("Transaction successful!");
//                updateBalance();
//            } else {
//                messageLabel.setText("Transaction failed!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
