package com.enrollment.dao;

import com.base.BaseDAO;
import com.enrollment.model.RequiredDocument;

import java.util.List;

public interface RequiredDocumentDAO extends BaseDAO<RequiredDocument> {
    //getRequiredDocumentsByStudentId
    List<RequiredDocument> findRequiredDocumentsByStudentId(Long studentId);
    //getRequiredDocumentsByStudentIdAndStatus - SUBMITTED / PENDING
    List<RequiredDocument> findRequiredDocumentsByStudentIdAndStatus(Long studentId, String status);
    //submitRequiredDocument    // change the status to SUBMITTED
    void submitRequiredDocument(Long studentId, String documentType);


}
