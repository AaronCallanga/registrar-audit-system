package com.documents.dao;

import com.config.DatabaseConnection;
import com.documents.mapper.DocumentMapper;
import com.documents.model.Document;
import com.exceptions.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class DocumentImplDAO implements DocumentDAO {

    @Override
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS documents (
                id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                document_type VARCHAR(255) NOT NULL,
                requested_by VARCHAR(255) NOT NULL,
                contact VARCHAR(255) UNIQUE NOT NULL,
                requested_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                released_date TIMESTAMP NULL
            );
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error while creating table: " + e.getMessage());
        }
    }

    @Override
    public Optional<Document> findById(Long id) {
        String sql = "SELECT * FROM documents WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(DocumentMapper.mapResultSetToDocument(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Error while fetching document: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Document> updateById(Long id, Document updatedDocument) {
        String sql = """
            UPDATE documents
            SET document_type = ?,
                requested_by = ?,
                contact = ?,
                requested_date = ?,
                released_date = ?
            WHERE id = ?
            RETURNING *;
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedDocument.getDocumentType());
            ps.setString(2, updatedDocument.getRequestedBy());
            ps.setString(3, updatedDocument.getContact());
            ps.setTimestamp(4, Timestamp.valueOf(updatedDocument.getRequestedDate()));
            if (updatedDocument.getReleaseDate() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(updatedDocument.getReleaseDate()));
            } else {
                ps.setNull(5, java.sql.Types.TIMESTAMP);
            }
            ps.setLong(6, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(DocumentMapper.mapResultSetToDocument(rs));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error updating document with ID " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM documents WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error deleting document with ID " + id, e);
        }
    }

    @Override
    public Optional<Document> save(Document document) {
        String sql = """
            INSERT INTO documents (document_type, requested_by, contact, requested_date, released_date)
            VALUES (?, ?, ?, ?, ?)
            RETURNING *;
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, document.getDocumentType());
            ps.setString(2, document.getRequestedBy());
            ps.setString(3, document.getContact());
            ps.setTimestamp(4, Timestamp.valueOf(document.getRequestedDate()));
            if (document.getReleaseDate() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(document.getReleaseDate()));
            } else {
                ps.setNull(5, java.sql.Types.TIMESTAMP);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(DocumentMapper.mapResultSetToDocument(rs));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error saving document", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Document> findAll() {
        String sql = "SELECT * FROM documents";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return DocumentMapper.mapResultSetToDocumentList(rs);

        } catch (Exception e) {
            throw new RuntimeException("Error fetching all documents", e);
        }
    }
}
