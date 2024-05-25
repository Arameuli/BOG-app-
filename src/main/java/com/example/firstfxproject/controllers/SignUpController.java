package com.example.firstfxproject.controllers;

import com.example.firstfxproject.dao.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button button_signUpp;
    @FXML
    private TextField tf_username_signup;
    @FXML
    private PasswordField tf_password_signup;
    @FXML
    private TextField tf_personalNumb;

    public TextField getTf_personalNumb() {
        return tf_personalNumb;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_signUpp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username_signup.getText().trim().isEmpty() && !tf_password_signup.getText().trim().isEmpty() && !tf_personalNumb.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event, Integer.parseInt(tf_personalNumb.getText()), tf_username_signup.getText(), tf_password_signup.getText());
                }else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up");
                    alert.show();
                }
            }
        });

    }
    public void signUpMain(){
        if(!tf_username_signup.getText().trim().isEmpty() && !tf_password_signup.getText().trim().isEmpty() && !tf_personalNumb.getText().trim().isEmpty()){
            DBUtils.signUpUser(null, Integer.parseInt(tf_personalNumb.getText()), tf_username_signup.getText(), tf_password_signup.getText());
        }else {
            System.out.println("Please fill in all information");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all information to sign up");
            alert.show();
        }
    }
}
