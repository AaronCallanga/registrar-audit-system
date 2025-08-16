package com.documents.mapper;

import com.documents.model.Document;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// Mapped the result set to Document object
public class DocumentMapper {
    public static Document mapResultSetToDocument(ResultSet rs) throws Exception {
        Timestamp releaseTs = rs.getTimestamp("released_date");
        return Document.builder()
                       .id(rs.getLong("id"))
                       .documentType(rs.getString("document_type"))
                       .requestedBy(rs.getString("requested_by"))
                       .contact(rs.getString("contact"))
                       .requestedDate(rs.getTimestamp("requested_date").toLocalDateTime())
                       .releaseDate(releaseTs != null ? releaseTs.toLocalDateTime() : null)
                       .build();
    }

    public static List<Document> mapResultSetToDocumentList(ResultSet rs) throws Exception {
        List<Document> documents = new ArrayList<>();
        while (rs.next()) {
            documents.add(mapResultSetToDocument(rs)); // reuse single mapper
        }
        return documents;
    }
}
