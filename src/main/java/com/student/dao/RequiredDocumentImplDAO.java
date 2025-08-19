package com.student.dao;

import com.config.DatabaseConnection;
import com.documents.mapper.DocumentMapper;
import com.student.mapper.RequiredDocumentMapper;
import com.student.model.RequiredDocument;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        String sql = "SELECT * FROM required_documents WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(RequiredDocumentMapper.mapResultSetToRequiredDocument(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Error while finding required document: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<RequiredDocument> updateById(Long id, RequiredDocument updatedDocument) {
        String sql = """
            UPDATE required_documents
            SET document_type = ?, status = ?, submitted_date = ?
            WHERE id = ?
            RETURNING *;
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedDocument.getDocumentType());
            ps.setString(2, updatedDocument.getStatus());

            if (updatedDocument.getSubmittedDate() != null) {
                ps.setTimestamp(3, Timestamp.valueOf(updatedDocument.getSubmittedDate()));
            } else {
                ps.setNull(3, java.sql.Types.TIMESTAMP);
            }

            ps.setLong(4, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(RequiredDocumentMapper.mapResultSetToRequiredDocument(rs));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error updating required document with ID " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM required_documents WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while deleting required document: " + e.getMessage());
        }

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
