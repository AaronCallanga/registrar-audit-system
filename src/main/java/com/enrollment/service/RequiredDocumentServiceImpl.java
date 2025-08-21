package com.enrollment.service;

import com.enrollment.dao.RequiredDocumentDAO;
import com.enrollment.model.RequiredDocument;

import java.util.ArrayList;
import java.util.List;

public class RequiredDocumentServiceImpl implements RequiredDocumentService {

    private final RequiredDocumentDAO requiredDocumentDAO;

    public RequiredDocumentServiceImpl(RequiredDocumentDAO requiredDocumentDAO) {
        this.requiredDocumentDAO = requiredDocumentDAO;
    }

    @Override
    public List<RequiredDocument> getRequiredDocumentsByStudentId(Long studentId) {
        return requiredDocumentDAO.findRequiredDocumentsByStudentId(studentId);
    }

    @Override
    public List<RequiredDocument> getRequiredDocumentsByStudentIdAndStatus(Long studentId, String status) {
        return requiredDocumentDAO.findRequiredDocumentsByStudentIdAndStatus(studentId, status);
    }

    @Override
    public void submitRequiredDocument(Long studentId, String documentType) {
        requiredDocumentDAO.submitRequiredDocument(studentId, documentType);
    }

    @Override
    public List<RequiredDocument> createRequiredDocuments(RequiredDocument requiredDocument) {
        List<RequiredDocument> requiredDocuments = new ArrayList<>();
        requiredDocuments.add(
                requiredDocumentDAO.save(requiredDocument)
                    .orElseThrow(() -> new RuntimeException("Required document not created"))
                             );
        return requiredDocuments;
    }
}
