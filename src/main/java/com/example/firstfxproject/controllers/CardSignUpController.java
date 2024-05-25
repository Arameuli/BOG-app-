package com.example.firstfxproject.controllers;

import com.example.firstfxproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.ResourceBundle;

public class CardSignUpController implements Initializable {
    @FXML
    private TextField tf_cardNumber;
    @FXML
    private TextField tf_moneyOnCard;

    @FXML
    private TextField tf_PersNumb;

    @FXML
    private Button button_CardSave;

    public TextField getTf_cardNumber() {
        return tf_cardNumber;
    }

    public TextField getTf_moneyOnCard() {
        return tf_moneyOnCard;
    }

    public Button getButton_CardSave() {
        return button_CardSave;
    }
    SignUpController signUpController=new SignUpController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void cardSignUp(ActionEvent event) throws SQLException, IOException {
        if(!tf_cardNumber.getText().trim().isEmpty() && !tf_moneyOnCard.getText().trim().isEmpty() && !tf_PersNumb.getText().trim().isEmpty()){
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstfxpr", "root", "root");
            PreparedStatement psInsert = connection.prepareStatement("INSERT into cards(user_id, card_numb, money) values(?, ?, ?)");
            psInsert.setInt(1, Integer.parseInt(tf_PersNumb.getText()));
            psInsert.setString(2, getTf_cardNumber().getText());
            psInsert.setString(3, getTf_moneyOnCard().getText());
            psInsert.executeUpdate();
            Stage stage = new Stage();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Sign Up Your card!");
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Please fill in all information");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all information to sign up");
            alert.show();
        }
    }
}
