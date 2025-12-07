package com.example.ver1.controllers;

import com.example.ver1.Main;
import com.example.ver1.models.DuplicateUserException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    @FXML
    private void handleRegister(){
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
            showMessage("Please fill in all fields",true);
        }
        if(!email.contains("@")){
            showMessage("Invalid email format",true);
            return;
        }
        if(password.length()<6){
            showMessage("Password must be at least 6 characters",true);
            return;
        }

        try{
            Main.getSystemController().getAuthManager().register(username,password,email);

            showMessage("Registration successful!",false);

            //wait 1 second then go to login
            new Thread(() ->{
                try{
                    Thread.sleep(1000);
                    javafx.application.Platform.runLater(()->{
                            try{
                                Main.showLoginScreen();
                    } catch (Exception e) {
                                e.printStackTrace();
                            }
                    });
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }).start();
        } catch (DuplicateUserException e){
            showMessage(e.getMessage(),true);
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage(), true);
        }
    }

    @FXML
    private void handleBackToLogin(){
        try{
            Main.showLoginScreen();
        } catch (Exception e){
            showMessage("Cannot go back: " + e.getMessage(), true);
        }
    }

    private void showMessage(String message, boolean isError){
        messageLabel.setText(message);
        messageLabel.setTextFill(isError ? Color.RED : Color.GREEN);
        messageLabel.setVisible(true);

    }
}

/*
Feature Problem:

When you register you need to close the application for it to save into the file

 */


