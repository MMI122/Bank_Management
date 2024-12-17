package com.example.managementapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Databaseprofile {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username_profile, String First_Name,String Last_Name,String email,String address_profile,String nid_profile ,String nationality_profile,String occupation_profile,String password_profile,String creditor_profile) {
        Parent root = null;
        if (username_profile != null && First_Name != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Databaseprofile.class.getResource(fxmlFile));
                root = loader.load();
                sonaliclient controller = loader.getController();
                double balance = getUserBalance(username_profile);
                controller.setUserInformation(username_profile, First_Name, Last_Name,email,address_profile,nid_profile,nationality_profile,occupation_profile,password_profile,creditor_profile,balance);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Databaseprofile.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void signupuser_profile(ActionEvent event, String username_profile, String First_Name, String Last_Name, String email, String address_profile, String nid_profile, String nationality_profile, String occupation_profile, String password_profile, String creditor_profile) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/profilework", "root", "");//password silo ekhane1
            psCheckUserExists = connection.prepareStatement("SELECT * FROM dataforprofile WHERE username_profile=?");
            psCheckUserExists.setString(1, username_profile);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This username is already taken.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement(
                        "INSERT INTO dataforprofile (username_profile,First_Name,Last_Name,email,address_profile,nid_profile,nationality_profile,occupation_profile,password_profile,creditor_profile,balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)"
                );
                psInsert.setString(1, username_profile);
                psInsert.setString(2,First_Name );
                psInsert.setString(3,Last_Name );
                psInsert.setString(4,email );
                psInsert.setString(5,address_profile );
                psInsert.setString(6,nid_profile );
                psInsert.setString(7,nationality_profile );
                psInsert.setString(8,occupation_profile );
                psInsert.setString(9,password_profile );
                psInsert.setString(10,creditor_profile );
                psInsert.executeUpdate();
                changeScene(event, "Sonali.fxml", "Welcome!", username_profile, First_Name, Last_Name, email, address_profile,nid_profile,nationality_profile,occupation_profile,password_profile,creditor_profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (psInsert != null) psInsert.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loginuser_profile(ActionEvent event, String username_profile, String password_profile) {
        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/profilework", "root", "");//password silo ekhane2
            psCheckUserExists = connection.prepareStatement("SELECT * FROM dataforprofile WHERE username_profile=? AND password_profile=?");
            psCheckUserExists.setString(1, username_profile);
            psCheckUserExists.setString(2, password_profile);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                String First_Name = resultSet.getString("First_Name");
                String Last_Name = resultSet.getString("Last_Name");
                String email = resultSet.getString("email");
                String address_profile = resultSet.getString("address_profile");
                String nid_profile = resultSet.getString("nid_profile");
                String nationality_profile = resultSet.getString("nationality_profile");
                String occupation_profile = resultSet.getString("occupation_profile");
                String creditor_profile = resultSet.getString("creditor_profile");
                String password_profile_db = resultSet.getString("password_profile");

                // Redirect to the user's profile page
                changeScene(event, "Sonali.fxml", "Welcome!", username_profile, First_Name, Last_Name, email,
                        address_profile, nid_profile, nationality_profile, occupation_profile,
                        password_profile_db, creditor_profile);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid username or password.");
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (psCheckUserExists != null) psCheckUserExists.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static double getUserBalance(String username_profile) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/profilework", "root", "");//password silo ekhane3
            ps = connection.prepareStatement("SELECT balance FROM dataforprofile WHERE username_profile=?");
            ps.setString(1, username_profile);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
