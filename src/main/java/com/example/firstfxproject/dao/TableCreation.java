package com.example.firstfxproject.dao;

import java.sql.*;

public class TableCreation {

    public void createTableUsers() {
        String url = "jdbc:mysql://localhost:3306/firstfxpr";
        String username = "root";
        String password = "root";
        String tableName = "users";

        String createTableSQL = "CREATE TABLE `users` ("
                + "`user_id` int unsigned NOT NULL AUTO_INCREMENT,"
                + "`username` varchar(45) NOT NULL,"
                + "`password` varchar(45) NOT NULL,"
                + "PRIMARY KEY (`user_id`)"
                + ")";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String checkTableExistenceSQL = "SHOW TABLES LIKE '" + tableName + "'";
            ResultSet resultSet = statement.executeQuery(checkTableExistenceSQL);

            if (resultSet.next()) {
                System.out.println("Table already exists. Skipping creation.");
            } else {
                statement.executeUpdate(createTableSQL);
                System.out.println("Table created successfully!");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTableCards() {
        String url = "jdbc:mysql://localhost:3306/firstfxpr";
        String username = "root";
        String password = "root";
        String tableName = "cards";

        String createTableSQL = "CREATE TABLE `cards` ("
                + "`user_id` int unsigned NOT NULL,"
                + "`card_numb` varchar(45),"
                + "`money` varchar(45),"
                + "PRIMARY KEY (`user_id`)"
                + ")";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String checkTableExistenceSQL = "SHOW TABLES LIKE '" + tableName + "'";
            ResultSet resultSet = statement.executeQuery(checkTableExistenceSQL);

            if (resultSet.next()) {
                System.out.println("Table already exists. Skipping creation.");
            } else {
                statement.executeUpdate(createTableSQL);
                System.out.println("Table created successfully!");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}