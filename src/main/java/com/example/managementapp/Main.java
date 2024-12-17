//Eight
//package com.example.managementapp;

//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Log In!");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.show();
//
//
//
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}
package com.example.managementapp;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the background image
        Image backgroundImage = new Image(getClass().getResource("background.jpg").toExternalForm()); // Replace with your background image name
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.DEFAULT.getWidth(),
                        BackgroundSize.DEFAULT.getHeight(),
                        true,
                        true,
                        false,
                        true
                )
        );

        // Create a StackPane for the splash screen
        StackPane splashPane = new StackPane();
        splashPane.setBackground(new Background(bgImage));

        // Add the animated GIF on top of the background
        Image gifImage = new Image(getClass().getResource("State Bank.gif").toExternalForm());
        ImageView gifView = new ImageView(gifImage);

        // Set the size of the GIF
        gifView.setFitWidth(600); // Adjust width based on your preference
        gifView.setPreserveRatio(true); // Keep aspect ratio

        // Add the GIF to the StackPane
        splashPane.getChildren().add(gifView);

        // Set the scene for the splash screen
        Scene splashScene = new Scene(splashPane, 600, 400); // Match the size of your GIF

        // Show the splash screen
        primaryStage.setTitle("Welcome to State Bank");
        primaryStage.setScene(splashScene);
        primaryStage.show();

        // Pause for 10 seconds (duration of the GIF) before transitioning to the login page
        PauseTransition pause = new PauseTransition(Duration.seconds(10)); // Adjusted to 10 seconds for your GIF
        pause.setOnFinished(event -> {
            try {
                // After the pause, load the login page
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene loginScene = new Scene(root, 600, 400);//600,400 silo
                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Log In!");
                //primaryStage.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start the pause transition
        pause.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
