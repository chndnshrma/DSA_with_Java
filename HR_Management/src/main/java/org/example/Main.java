package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Loading FXML...");

            // Load the FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/email-ui.fxml"));
            Parent root = loader.load();

            System.out.println("FXML loaded successfully");

            // Set up the stage
            primaryStage.setTitle("HR System - Employee Email Manager");
            primaryStage.setScene(new Scene(root, 800, 900));
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(900);
            primaryStage.show();

            System.out.println("Application started successfully");

        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();

            // Show error dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Application Error");
            alert.setHeaderText("Failed to start HR System");
            alert.setContentText("Error: " + e.getMessage() + "\n\nCheck the console for details.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting HR System...");
        try {
            launch(args);
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}