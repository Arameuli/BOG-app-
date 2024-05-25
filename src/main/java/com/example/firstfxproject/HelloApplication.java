package com.example.firstfxproject;

import com.example.firstfxproject.dao.TableCreation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Log in!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        TableCreation tableCreation=new TableCreation();
        tableCreation.createTableUsers();
        tableCreation.createTableCards();
        launch();
    }
}