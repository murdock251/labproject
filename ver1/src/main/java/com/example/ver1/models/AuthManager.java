package com.example.ver1.models;

import java.nio.file.attribute.UserPrincipal;

public class AuthManager {
    private UserManager userManager;
    private User currentUser;

    public AuthManager(UserManager userManager){
        this.userManager = userManager;
        this.currentUser = null;
    }

    public void register(String username,String password,String email) throws DuplicateUserException{
        if(userManager.usernameExists(username)){
            throw new DuplicateUserException("Username '" + username + "' already exists!");
        }

        if(password.length() < 6){
            throw new IllegalArgumentException("Password must be at least 6 Characters!");
        }

        User newUser = new User(username,password,email);
        userManager.addUser(newUser);
        System.out.println("Registration successful!");
    }

    public void login(String username,String password) throws InvalidLoginException{
        User user = userManager.getUserByUsername(username);

        if(user == null){
            throw new InvalidLoginException("Username does not exist!");
        }
        if(!user.getPassword().equals(password)){
            throw new InvalidLoginException("Incorrect password!");
        }

        currentUser = user;
        System.out.println("Login successful! Welcome, " + username);
    }

    public void logout(){
        if(currentUser!=null){
            System.out.println("Goodbye," + currentUser.getUsername());
            currentUser = null;
        }
    }

    public boolean isLoggedIn(){
        return currentUser != null;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    //Just for testing

//    public static void main(String[] args) {
//        UserManager um = new UserManager();
//        AuthManager am = new AuthManager(um);
//
//        try{
//            am.register("nafis","pass1234","john@gmail.com");
//            System.out.println(" Registration worked");
//        }
//        catch (DuplicateUserException e){
//            System.out.println(e.getMessage());
//        }
//        try{
//            am.register("nafis","pass1234","john@gmail.com");
//            System.out.println(" Registration worked");
//        }
//        catch (DuplicateUserException e){
//            System.out.println("caught duplicate " + e.getMessage());
//        }
//
//        try{
//            am.login("nafis", "pass1234");
//            System.out.println("login worked");
//        }
//        catch(InvalidLoginException e){
//            System.out.println(e.getMessage());
//        }
//    }

}
