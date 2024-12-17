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

public class DbUtils {
    //will do all the communication with the database including signing up users,logging the user and changing the scene
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String favChannel) {
        Parent root = null;
        if (username != null && favChannel != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DbUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username, favChannel);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DbUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //will change the scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    public static void signUpUser(ActionEvent event, String username, String password, String favChannel) {

        //will sign up the user
        //will change the scene
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultset = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "");////password silo ekhane6
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            psCheckUserExists.setString(1, username);
            resultset = psCheckUserExists.executeQuery();
            if (resultset.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users(username,password,favChannel) VALUES(?,?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, favChannel);
                psInsert.executeUpdate();
                changeScene(event, "logged-in.fxml", "Welcome!", username, favChannel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        //will log in the user
        //will change the scene
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "?");//password silo ekhane5
            preparedStatement=connection.prepareStatement("SELECT password,favChannel FROM users WHERE username=?");
            preparedStatement.setString(1,username);
            resultset=preparedStatement.executeQuery();
            if(!resultset.isBeforeFirst()){
                System.out.println("Users not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            }else{
                while(resultset.next()){
                    String retrievedPassword = resultset.getString("password");
                    String retrievedChannel = resultset.getString("favChannel");
                    if(retrievedPassword.equals(password)){
                        changeScene(event,"logged-in.fxml","Welcome!",username,retrievedChannel);

                    }else {
                        System.out.println("Passwords did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultset!=null){
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
