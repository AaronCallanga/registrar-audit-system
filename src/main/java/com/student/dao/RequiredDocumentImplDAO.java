package com.student.dao;

import com.config.DatabaseConnection;
import com.student.model.RequiredDocument;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class RequiredDocumentImplDAO implements RequiredDocumentDAO {

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS required_documents (\n" +
                "    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
                "    student_id INT NOT NULL,\n" +
                "    document_type VARCHAR(255) NOT NULL,\n" +
                "    status VARCHAR(255) NOT NULL,\n" +
                "    submitted_date TIMESTAMP,\n" +
                "    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE\n" +
                ");";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while creating table required_documents: " + e.getMessage());
        }
    }

    @Override
    public Optional<RequiredDocument> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<RequiredDocument> updateById(Long id, RequiredDocument obj) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<RequiredDocument> save(RequiredDocument obj) {
        return Optional.empty();
    }

    @Override
    public List<RequiredDocument> findAll() {
        return List.of();
    }
}
