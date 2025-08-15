package com.documents.dao;

import com.config.DatabaseConnection;
import com.documents.mapper.DocumentMapper;
import com.documents.model.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

public class DocumentImplDAO implements DocumentDAO {

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS documents (\n" +
                "    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
                "    document_type VARCHAR(255) NOT NULL,\n" +
                "    requested_by VARCHAR(255) NOT NULL,\n" +
                "    contact VARCHAR(255) UNIQUE NOT NULL,\n" +
                "    email VARCHAR(255) UNIQUE  NOT NULL,\n" +
                "    requested_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                "    released_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                ");\n";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error while creating table: " + e.getMessage());
        }
    }

    @Override
    public Document findById(Long id) {
        String sql = "SELECT * FROM documents WHERE id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return DocumentMapper.mapResultSetToDocument(resultSet);
            }
            throw new RuntimeException("Document with id " + id + " not found");

        } catch (Exception e) {
            System.out.println("Error while creating table: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Document updateById(Long id, Document obj) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Document save(Document obj) {
        return null;
    }

    @Override
    public List<Document> findAll() {
        return List.of();
    }
}
