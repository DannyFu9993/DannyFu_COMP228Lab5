package com.example.dannyfu_comp228lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Danny Fu Lab 5");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        launch();


        //testing purpose only, to see if I actually can connect and insert data
        //Note: success

        // Testing purpose only, to see if I can connect and insert data
//        Connection connection = DatabaseConnection.getConnection();
//        String insertGameSQL = "INSERT INTO Game (game_title) VALUES (?)";
//        PreparedStatement prepStatement = connection.prepareStatement(insertGameSQL);
//        prepStatement.setString(1, "STALKER 2: Heart of Chornobyl");
//        prepStatement.executeUpdate();
//        prepStatement.close();
//        connection.close();
    }
}