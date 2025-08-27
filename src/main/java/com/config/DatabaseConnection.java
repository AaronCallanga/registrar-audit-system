package com.config;

import com.documents.dao.DocumentRequestDAO;
import com.documents.dao.DocumentRequestImplDAO;
import com.enrollment.dao.RequiredDocumentDAO;
import com.enrollment.dao.RequiredDocumentImplDAO;
import com.enrollment.dao.StudentDAO;
import com.enrollment.dao.StudentImplDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        final String dbUrl = "jdbc:postgresql://localhost:5432/registrar"; // change database
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

    public static void setUpTables() {
        DocumentRequestDAO documentDAO = new DocumentRequestImplDAO();
        RequiredDocumentDAO requiredDocumentDAO = new RequiredDocumentImplDAO();
        StudentDAO studentDAO = new StudentImplDAO();

        documentDAO.createTable();
        studentDAO.createTable();
        requiredDocumentDAO.createTable();

    }

}
