package com.example.dannyfu_comp228lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        String dbUrl = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD"; // Use the correct hostname, port, and SID
        String user = "COMP228_F24_soh_42"; // Your Oracle username
        String password = "Danny1234"; // Your Oracle password

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}
