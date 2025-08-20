package com.student.service;

import com.student.dao.RequiredDocumentDAO;
import com.student.model.RequiredDocument;

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
    public void submitRequiredDocument(Long studentId, Long requiredDocumentId) {

    }
}
