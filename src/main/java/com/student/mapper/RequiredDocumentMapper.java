package com.student.mapper;

import com.student.model.RequiredDocument;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RequiredDocumentMapper {
    public static RequiredDocument mapResultSetToRequiredDocument(ResultSet rs) throws Exception {

        Timestamp submittedDate = Timestamp.valueOf(rs.getString("submitted_date"));

        RequiredDocument requiredDocument = RequiredDocument.builder()
                .id(rs.getLong("id"))
                .studentId(rs.getLong("student_id"))
                .documentType(rs.getString("document_type"))
                .status(rs.getString("status"))
                .submittedDate(submittedDate != null ? submittedDate.toLocalDateTime() : null)
                .build();
        return requiredDocument;
    }

    public static List<RequiredDocument> mapResultSetToRequiredDocumentList(ResultSet rs) throws Exception {
        List<RequiredDocument> requiredDocuments = new ArrayList<>();
        while (rs.next()) {
            requiredDocuments.add(mapResultSetToRequiredDocument(rs));
        }
        return requiredDocuments;
    }
}
