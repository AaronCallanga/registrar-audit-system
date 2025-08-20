package com.student.dao;

import com.base.BaseDAO;
import com.student.model.RequiredDocument;

import java.util.List;

public interface RequiredDocumentDAO extends BaseDAO<RequiredDocument> {
    //getRequiredDocumentsByStudentId
    List<RequiredDocument> findRequiredDocumentsByStudentId(Long studentId);
    //getRequiredDocumentsByStudentIdAndStatus - SUBMITTED / PENDING
    List<RequiredDocument> findRequiredDocumentsByStudentIdAndStatus(Long studentId, String status);
    //submitRequiredDocument    // change the status to SUBMITTED
    void submitRequiredDocument(String documentType);


}
