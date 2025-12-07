package com.example.ver2;

import com.example.ver2.controllers.SystemController;
import com.example.ver2.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private static SystemController systemController;
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        systemController = new SystemController();
        systemController.initialize();
        showLoginScreen();
        primaryStage.setOnCloseRequest(event -> {
            systemController.saveAll();
            System.out.println("Application closed");
        });
    }

    public static void showLoginScreen() throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        primaryStage.setTitle("PostHub- Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void showRegisterScreen() throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("register.fxml"));
        primaryStage.setTitle("Register Screen");
        primaryStage.setScene(new Scene(root, 400,500));
        primaryStage.show();
    }
    public static void showFeedScreen() throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("feed.fxml"));
        primaryStage.setTitle("Feed Screen");
        primaryStage.setScene(new Scene(root, 700,600));
        primaryStage.show();
    }
    public static void showCreatePostScreen() throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("createPost.fxml"));
        primaryStage.setTitle("Create Post");
        primaryStage.setScene(new Scene(root, 500,400));
        primaryStage.show();
    }
    public static void showPostDetailScreen() throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("postDetail.fxml"));
        primaryStage.setTitle("Post Details");
        primaryStage.setScene(new Scene(root, 600,500));
        primaryStage.show();
    }

    public static SystemController getSystemController(){
        return systemController;
    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
