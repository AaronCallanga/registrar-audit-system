package com.enrollment.facade;

import com.enrollment.model.RequiredDocument;

import java.util.List;

public interface RequiredDocumentsFacade {
    void viewRequiredDocumentsByStudentId();
    void viewSubmittedRequiredDocumentsByStudentId();
    void viewMissingRequiredDocumentsByStudentId();
    void submitRequiredDocument();
}
