package com.example.ooplabtask3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginApp extends Application {
    private static final String CREDENTIALS_FILE = "C:\\Users\\Abduallah\\Documents\\SP24-BSE-019\\oopLABTASK3\\credentials.txt";
    private final Map<String, String> credentials = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        loadCredentials();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(40);
        grid.setHgap(40);

        grid.setStyle("-fx-background-color:#AEC6CF");

        HBox headingBox = new HBox();
        headingBox.setStyle("-fx-alignment: center; -fx-border-color: white; -fx-border-width: 2px 0 2px 0;");
        Label formHeadings = new Label("University Registration Form:");
        formHeadings.setStyle("-fx-font-size: 30px; -fx-text-fill:black; -fx-font-weight: bold;");
        headingBox.getChildren().add(formHeadings);

        grid.add(headingBox, 0, 0, 2, 1);

        ImageView bannerImageView = new ImageView(new Image("file:C:\\Users\\Abduallah\\Pictures\\Picture7.jpg"));
        bannerImageView.setFitWidth(600);
        bannerImageView.setFitHeight(400);
        bannerImageView.setPreserveRatio(false);
        grid.add(bannerImageView, 0, 1, 2, 1);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");
        Label notificationLabel = new Label();

        grid.add(usernameLabel, 0, 2);
        grid.add(usernameField, 1, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(loginButton, 0, 4);
        grid.add(exitButton, 1, 4);
        grid.add(notificationLabel, 0, 5, 2, 1);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            System.out.println("Username entered: " + username + ", Password entered: " + password);

            if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                showWelcomeWindow(username);
            } else {
                notificationLabel.setText("Incorrect Username or Password");
            }
        });

        exitButton.setOnAction(e -> primaryStage.close());

        Scene scene = new Scene(grid, 800, 800);
        primaryStage.setTitle("University Login Portal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCredentials() {
        File file = new File(CREDENTIALS_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        credentials.put(parts[0].trim(), parts[1].trim());
                    }
                }
                System.out.println("Loaded credentials: " + credentials);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Credentials file not found: " + CREDENTIALS_FILE);
        }
    }
    private void showWelcomeWindow(String username) {
        Stage welcomeStage = new Stage();
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        Scene scene = new Scene(welcomeLabel, 400, 400);
        welcomeStage.setScene(scene);
        welcomeStage.setTitle("Welcome");
        welcomeStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
