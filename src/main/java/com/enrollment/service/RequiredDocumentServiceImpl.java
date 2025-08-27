package com.enrollment.service;

import com.enrollment.dao.RequiredDocumentDAO;
import com.enrollment.model.RequiredDocument;
import com.exceptions.EntityPersistenceException;
import com.exceptions.ResourceNotFoundException;

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
        List<RequiredDocument> requiredDocuments = getRequiredDocumentsByStudentId(studentId);
        boolean isDocumentRequired = requiredDocuments.stream()
                                                      .anyMatch(requiredDocument ->
                                                                      requiredDocument.getDocumentType().equalsIgnoreCase(documentType)
                                                               );
        if (isDocumentRequired) {
            requiredDocumentDAO.submitRequiredDocument(studentId, documentType);
            System.out.println("Document '" + documentType + "' submitted successfully for student ID: " + studentId);
        } else {
            System.out.println("Document '" + documentType + "' is not required for student ID: " + studentId);
        }
    }

    @Override
    public RequiredDocument createRequiredDocuments(RequiredDocument requiredDocument) {
        return requiredDocumentDAO.save(requiredDocument)
                    .orElseThrow(() -> new EntityPersistenceException("Required document not created"));
    }
}
