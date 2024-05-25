package com.example.firstfxproject.dao;

import java.io.IOException;
import java.sql.*;

import com.example.firstfxproject.HelloApplication;
import com.example.firstfxproject.controllers.CardSignUpController;
import com.example.firstfxproject.controllers.HelloController;
import com.example.firstfxproject.controllers.LoggedInController;
//import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;


public class DBUtils {
    public static void signUpUser(ActionEvent event, int id, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstfxpr", "root", "root");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT into users(user_id, username, password) values(?, ?, ?)");
                psInsert.setInt(1, id);
                psInsert.setString(2, username);
                psInsert.setString(3, password);
                psInsert.executeUpdate();
                Stage stage = new Stage();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp_card.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Sign Up Your card!");
                stage.setScene(scene);
                stage.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loginUser(ActionEvent event, String username, String password, Stage stage) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstfxpr", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("loggedIn.fxml"));

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String returnedPassword = resultSet.getString("password");
                    if (returnedPassword.equals(password)) {
                        PreparedStatement prep1 = connection.prepareStatement("SELECT c.money FROM users u JOIN cards c ON u.user_id = c.user_id WHERE u.username = ? AND u.password = ?");
                        prep1.setString(1, username);
                        prep1.setString(2, password);
                        ResultSet resultSet1 = prep1.executeQuery();
                        if (resultSet1.next()) {
                            String money = resultSet1.getString("money");
                            try{
                                ((Node) (event.getSource())).getScene().getWindow().hide();
                                Scene scene = new Scene(loader.load());
                                stage.setTitle("Welcome!");
                                stage.setScene(scene);
                                LoggedInController loggedInController = loader.getController();
                                loggedInController.setUserInformation(username, money);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else{
                            ((Node) (event.getSource())).getScene().getWindow().hide();
                            Scene scene = new Scene(loader.load());
                            stage.setTitle("Welcome!");
                            stage.setScene(scene);
                            LoggedInController loggedInController = loader.getController();
                            loggedInController.setUserInformation(username, "0.00");
                            stage.show();
                        }
                    } else {
                        System.out.println("Passwords didn't match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
