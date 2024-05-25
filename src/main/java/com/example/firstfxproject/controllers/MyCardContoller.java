package com.example.firstfxproject.controllers;

import com.example.firstfxproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MyCardContoller {
    @FXML
    private Label tf_YourCash;
    @FXML
    private Label tf_YourNumb;


    public void backToHello(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void setCardInformation(String money, String cardNumber){
        tf_YourCash.setText(money);
        tf_YourNumb.setText(cardNumber);
    }
}
