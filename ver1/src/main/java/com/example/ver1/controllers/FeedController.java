package com.example.ver1.controllers;

import com.example.ver1.Main;
import com.example.ver1.models.Post;
import com.example.ver1.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class FeedController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private ListView<String> postListView;

    private ArrayList<Post> currentPosts;
    private static Post selectedPost;

    @FXML
    public void initialize() {
        System.out.println("=== FEED CONTROLLER INITIALIZE ===");

        // Check if components are injected
        System.out.println("welcomeLabel is null? " + (welcomeLabel == null));
        System.out.println("postListView is null? " + (postListView == null));

        if (postListView == null || welcomeLabel == null) {
            System.err.println("ERROR: Components not injected! Check fx:id in FXML!");
            return;
        }

        loadPosts();

        User currentUser = Main.getSystemController().getAuthManager().getCurrentUser();

        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getUsername() + "!");
            System.out.println("Welcome message set for: " + currentUser.getUsername());
        } else {
            System.err.println("WARNING: No current user!");
        }
    }

    private void loadPosts() {
        System.out.println("=== LOADING POSTS ===");
        currentPosts = Main.getSystemController().getPostManager().getAllPosts();
        System.out.println("Found " + currentPosts.size() + " posts");

        ObservableList<String> postStrings = FXCollections.observableArrayList();

        for (Post post : currentPosts) {
            String displayText = "[" + post.getPostType() + "] " +
                    post.getAuthorName() + ": " +
                    post.getContent().substring(0, Math.min(50, post.getContent().length())) +
                    (post.getContent().length() > 50 ? "..." : "") +
                    " | Likes: " + post.getLikeCount() +
                    " | Comments: " + post.getCommentCount();

            postStrings.add(displayText);
            System.out.println("Added post: " + displayText);
        }

        postListView.setItems(postStrings);
        System.out.println("Posts loaded into ListView");
    }

    @FXML
    private void handleCreatePost() {
        System.out.println("=== CREATE POST BUTTON CLICKED ===");
        try {
            Main.showCreatePostScreen();
        } catch (Exception e) {
            System.err.println("Error opening create post screen: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Cannot open create post screen: " + e.getMessage());
        }
    }

    @FXML
    private void handleViewDetails() {
        System.out.println("=== VIEW DETAILS BUTTON CLICKED ===");
        int selectedIndex = postListView.getSelectionModel().getSelectedIndex();
        System.out.println("Selected index: " + selectedIndex);

        if (selectedIndex == -1) {
            System.out.println("No post selected");
            showAlert("No Selection", "Please select a post to view details");
            return;
        }

        selectedPost = currentPosts.get(selectedIndex);
        System.out.println("Selected post: " + selectedPost.getPostId());

        try {
            Main.showPostDetailScreen();
        } catch (Exception e) {
            System.err.println("Error opening post details: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Cannot open post details: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        System.out.println("=== REFRESH BUTTON CLICKED ===");
        loadPosts();
        showAlert("Refreshed", "Feed refreshed successfully");
    }

    @FXML
    private void handleLogout() {
        System.out.println("=== LOGOUT BUTTON CLICKED ===");
        Main.getSystemController().getAuthManager().logout();
        Main.getSystemController().saveAll();
        System.out.println("Data saved, returning to login...");

        try {
            Main.showLoginScreen();
        } catch (Exception e) {
            System.err.println("Error logging out: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Cannot logout: " + e.getMessage());
        }
    }

    public static Post getSelectedPost() {
        return selectedPost;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
