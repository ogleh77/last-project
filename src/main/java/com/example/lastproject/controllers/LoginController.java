package com.example.lastproject.controllers;

import animatefx.animation.FadeIn;
import com.example.lastproject.entities.Users;
import com.example.lastproject.generalize.GeneralClass;
import com.example.lastproject.models.UsersDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController extends GeneralClass implements Initializable {


    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<Users> userCombo;

    @FXML
    private Label validionText;

    private UsersDAO usersDAO;

    public LoginController() {
        this.usersDAO = new UsersDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            try {
                userCombo.setItems(usersDAO.getAll());
            } catch (SQLException e) {

                e.printStackTrace();
            }
        });
    }

    @FXML
    void loginHandler(ActionEvent event) {
        Users users = userCombo.getValue();
        if (users.getPassword().equals(password.getText())) {
            System.out.println("Matched...");
        } else {
            FadeIn fadeIn = new FadeIn();
            fadeIn.setNode(validionText);
            validionText.setVisible(true);
            fadeIn.play();
        }

    }

    @FXML
    void exitHandler(MouseEvent event) {

    }

}
