package com.config;

import com.documents.dao.DocumentDAO;
import com.documents.dao.DocumentImplDAO;
import com.student.dao.RequiredDocumentDAO;
import com.student.dao.RequiredDocumentImplDAO;
import com.student.dao.StudentDAO;
import com.student.dao.StudentImplDAO;
import com.student.model.RequiredDocument;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

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
        DocumentDAO documentDAO = new DocumentImplDAO();
        RequiredDocumentDAO requiredDocumentDAO = new RequiredDocumentImplDAO();
        StudentDAO studentDAO = new StudentImplDAO();

        documentDAO.createTable();
        requiredDocumentDAO.createTable();
        studentDAO.createTable();
    }

}
