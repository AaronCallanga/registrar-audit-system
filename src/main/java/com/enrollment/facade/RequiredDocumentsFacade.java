package com.enrollment.facade;

import com.enrollment.model.RequiredDocument;

import java.util.List;

public interface RequiredDocumentsFacade {
    void getRequiredDocumentsByStudentId();
    void getRequiredDocumentsByStudentIdAndStatus(); // can be fetch by status (missing/submitted)) or by date (if submiited date is null)
    void submitRequiredDocument();
}
