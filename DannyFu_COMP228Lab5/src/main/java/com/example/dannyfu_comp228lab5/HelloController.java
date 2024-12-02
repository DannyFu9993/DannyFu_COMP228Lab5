package com.example.dannyfu_comp228lab5;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private Label welcomeText;

    // Game Table Section ====================================
    @FXML
    private TextField gameTitle;

    // Player Table Section ====================================
    @FXML
    private TextField playerFirstName;
    @FXML
    private TextField playerLastName;
    @FXML
    private TextField playerAddress;
    @FXML
    private TextField playerPostal;
    @FXML
    private TextField playerProvince;
    @FXML
    private TextField playerPhone;

    // Updating Player Section ====================================
    @FXML
    private TextField updatePlayerID;
    @FXML
    private TextField updateFirstName;
    @FXML
    private TextField updateLastName;
    @FXML
    private TextField updateAddress;
    @FXML
    private TextField updatePostal;
    @FXML
    private TextField updateProvince;
    @FXML
    private TextField updatePhone;


    //For Player And Game table
    @FXML
    private TextField lookupIDText;

    //TableView and its columns
    @FXML
    private TableView<PlayerAndGame> playerGameTable;
    @FXML
    private TableColumn<PlayerAndGame, Integer> playerGameIDCol;
    @FXML
    private TableColumn<PlayerAndGame, Integer> gameIDCol;
    @FXML
    private TableColumn<PlayerAndGame, Integer> playerIDCol;
    @FXML
    private TableColumn<PlayerAndGame, String> playingDateCol;
    @FXML
    private TableColumn<PlayerAndGame, Integer> scoreCol;


    //connecting to the Database ====================================
    @FXML

    //Global variable to for connection ====================================
    Connection connection;



    @FXML
    protected void onConnectToDB() {
        connection = DatabaseConnection.getConnection();
        System.out.println("Database connected!");
    }

    @FXML
    protected void onGameSubmit() throws SQLException {
        //Retreive input text
        String gameTitleInput = gameTitle.getText();
        //
        String insertTitle = "INSERT INTO Game (game_title) VALUES (?)";
        PreparedStatement prepStatement = connection.prepareStatement(insertTitle);
        prepStatement.setString(1, gameTitleInput);
        prepStatement.executeUpdate();
        prepStatement.close();

        // Clean up so it's easier to add again
        gameTitle.clear();

        System.out.println("Game" +  gameTitleInput + " has been inserted!");
        //        connection.close();
    }

    @FXML
    protected void onSubmitPlayer() throws SQLException {
        // Retrieve input text from the text boxes
        String firstName = playerFirstName.getText();
        String lastName = playerLastName.getText();
        String address = playerAddress.getText();
        String postalCode = playerPostal.getText();
        String province = playerProvince.getText();
        String phoneNumber = playerPhone.getText();

        // Insert player data into the database
        String insertPlayer = "INSERT INTO Player (first_name, last_name, address, postal_code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement prepStatement = connection.prepareStatement(insertPlayer);

        prepStatement.setString(1, firstName);
        prepStatement.setString(2, lastName);
        prepStatement.setString(3, address);
        prepStatement.setString(4, postalCode);
        prepStatement.setString(5, province);
        prepStatement.setString(6, phoneNumber);

        // Execute the query
        prepStatement.executeUpdate();

        // Close resources
        prepStatement.close();
        connection.close();

        // Clear the text fields after submission
        playerFirstName.clear();
        playerLastName.clear();
        playerAddress.clear();
        playerPostal.clear();
        playerProvince.clear();
        playerPhone.clear();

        System.out.println("Player inserted successfully!");
    }

    @FXML
    protected void onSubmitUpdate() throws SQLException {
        //Retrieve input
        String playerIdInput = updatePlayerID.getText();
        String firstName = updateFirstName.getText();
        String lastName = updateLastName.getText();
        String address = updateAddress.getText();
        String postalCode = updatePostal.getText();
        String province = updateProvince.getText();
        String phoneNumber = updatePhone.getText();

        // Check if player exists
        boolean playerExists = false;
        String checkPlayer = "SELECT 1 FROM Player WHERE player_id = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkPlayer);
        checkStatement.setString(1, playerIdInput);
        playerExists = checkStatement.executeQuery().next();
        checkStatement.close();

        if (!playerExists) {
            System.out.println("Not updated because player ID not found");
        }
        else {

            //Will not update certain fields if they are empty. Will only update the ones that you typed
            if (!firstName.isEmpty()) {
                String updateFirstName = "UPDATE Player SET first_name = ? WHERE player_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateFirstName);
                updateStatement.setString(1, firstName);
                updateStatement.setString(2, playerIdInput);
                updateStatement.executeUpdate();
                updateStatement.close();
            }

            if (!lastName.isEmpty()) {
                String updateLastName = "UPDATE Player SET last_name = ? WHERE player_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateLastName);
                updateStatement.setString(1, lastName);
                updateStatement.setString(2, playerIdInput);
                updateStatement.executeUpdate();
                updateStatement.close();
            }

            if (!address.isEmpty()) {
                String updateAddress = "UPDATE Player SET address = ? WHERE player_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateAddress);
                updateStatement.setString(1, address);
                updateStatement.setString(2, playerIdInput);
                updateStatement.executeUpdate();
                updateStatement.close();
            }

            if (!postalCode.isEmpty()) {
                String updatePostal = "UPDATE Player SET postal_code = ? WHERE player_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updatePostal);
                updateStatement.setString(1, postalCode);
                updateStatement.setString(2, playerIdInput);
                updateStatement.executeUpdate();
                updateStatement.close();
            }

            if (!province.isEmpty()) {
                String updateProvince = "UPDATE Player SET province = ? WHERE player_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateProvince);
                updateStatement.setString(1, province);
                updateStatement.setString(2, playerIdInput);
                updateStatement.executeUpdate();
                updateStatement.close();
            }

            if (!phoneNumber.isEmpty()) {
                String updatePhone = "UPDATE Player SET phone_number = ? WHERE player_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updatePhone);
                updateStatement.setString(1, phoneNumber);
                updateStatement.setString(2, playerIdInput);
                updateStatement.executeUpdate();
                updateStatement.close();
            }

            System.out.println("Player updated successfully!");
        }


    }

    @FXML
    public void initialize() {
        System.out.println("Initialized()");
        playerGameIDCol.setCellValueFactory(new PropertyValueFactory<>("playerGameID"));
        gameIDCol.setCellValueFactory(new PropertyValueFactory<>("gameID"));
        playerIDCol.setCellValueFactory(new PropertyValueFactory<>("playerID"));
        playingDateCol.setCellValueFactory(new PropertyValueFactory<>("playingDate"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
    }


    @FXML
    protected void onLookupID() throws SQLException {
        // Parse the player ID input as an integer
        int playerIdInput = Integer.parseInt(lookupIDText.getText());

        // Fetch data from PlayerAndGame table for the given player_id
        String fetchQuery = "SELECT player_game_id, game_id, player_id, playing_date, score FROM PlayerAndGame WHERE player_id = ?";
        PreparedStatement fetchStatement = connection.prepareStatement(fetchQuery);
        fetchStatement.setInt(1, playerIdInput);

        // Execute the query
        var fetchResults = fetchStatement.executeQuery();

        // Clear existing table data
        playerGameTable.getItems().clear();

        // Check if any rows were returned
        if (!fetchResults.isBeforeFirst()) {
            System.out.println("No data found for player_id: " + playerIdInput);
        } else {
            // Rows exist, process and populate the table
            while (fetchResults.next()) {
                playerGameTable.getItems().add(new PlayerAndGame(
                        fetchResults.getInt("player_game_id"),
                        fetchResults.getInt("game_id"),
                        fetchResults.getInt("player_id"),
                        fetchResults.getString("playing_date"),
                        fetchResults.getInt("score")
                ));
            }
            System.out.println("Data successfully loaded for player_id: " + playerIdInput);
        }

        // Close resources
        fetchResults.close();
        fetchStatement.close();
    }






}