package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        final String dbUrl = "jdbc:postgresql://localhost:5432/ciicc"; // change database
        final String username = "postgres";
        final String password = "admin";

        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            //System.out.println("Connection established");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return connection;
    }

}
