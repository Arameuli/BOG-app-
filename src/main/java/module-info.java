module com.example.firstfxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.firstfxproject to javafx.fxml;
    exports com.example.firstfxproject;
    exports com.example.firstfxproject.dao;
    opens com.example.firstfxproject.dao to javafx.fxml;
    exports com.example.firstfxproject.controllers;
    opens com.example.firstfxproject.controllers to javafx.fxml;
}