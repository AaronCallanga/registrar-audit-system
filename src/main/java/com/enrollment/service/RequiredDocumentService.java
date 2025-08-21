package com.enrollment.service;

import com.enrollment.model.RequiredDocument;

import java.util.List;

public interface RequiredDocumentService {
    //RequiredDocument getRequiredDocumentById(Long id);
    //void deleteRequiredDocumentById(Long id);
    //RequiredDocument enrollRequiredDocument(String name, String yearLevel, String program, String contact);
    //RequiredDocument updateRequiredDocumentInfo(String name, String yearLevel, String program, String contact);

    List<RequiredDocument> getRequiredDocumentsByStudentId(Long studentId);
    List<RequiredDocument> getRequiredDocumentsByStudentIdAndStatus(Long studentId, String status); // can be fetch by status (missing/submitted)) or by date (if submiited date is null)
    void submitRequiredDocument(Long studentId, String documentType);
    List<RequiredDocument> createRequiredDocuments(RequiredDocument requiredDocument);
}
