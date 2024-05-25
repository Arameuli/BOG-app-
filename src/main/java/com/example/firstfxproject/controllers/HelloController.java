package com.example.firstfxproject.controllers;

import com.example.firstfxproject.HelloApplication;
import com.example.firstfxproject.dao.DBUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button button_login;
    @FXML
    private Button button_signUp;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;


    public void signUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sign up!");
        stage.setScene(scene);
        stage.show();
    }

    public void logedIn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        DBUtils dbUtils = new DBUtils();
        dbUtils.loginUser(event, tf_username.getText(), tf_password.getText(), stage);

    }
}