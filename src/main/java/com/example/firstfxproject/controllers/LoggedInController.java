package com.example.firstfxproject.controllers;

import com.example.firstfxproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Button button_for_name;
    @FXML
    private Label cash;

    @FXML
    private Button button_myCard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUserInformation(String username, String money){
        button_for_name.setText(username);
        cash.setText(money);
    }

    public void myCard(ActionEvent event) throws IOException {
        Stage stage = new Stage();
//        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("myCard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Your Card!");
        stage.setScene(scene);
        stage.show();
    }
}
