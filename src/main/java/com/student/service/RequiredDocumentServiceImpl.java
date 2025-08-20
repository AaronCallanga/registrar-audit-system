package com.student.service;

import com.student.model.RequiredDocument;

import java.util.List;

public class RequiredDocumentServiceImpl implements RequiredDocumentService {

    @Override
    public List<RequiredDocument> getRequiredDocumentsByStudentId(Long studentId) {
        return List.of();
    }

    @Override
    public List<RequiredDocument> getRequiredDocumentsByStudentIdAndStatus(Long studentId, String status) {
        return List.of();
    }

    @Override
    public void submitRequiredDocument(Long studentId, Long requiredDocumentId) {

    }
}
