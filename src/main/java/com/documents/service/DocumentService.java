package com.documents.service;

import com.documents.model.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document getRequestById(Long id);
    void deleteRequestById(Long id);
    List<Document> getAllRequest();
    Document createRequest(String documentType, String requestedBy, String contact);
    Document updateRequest(Long documentId, String documentType, String requestedBy, String contact);
    List<Document> getOngoingRequests();
    List<Document> getCompletedRequests();

    void releaseDocument(Long id);
    void setUpTable();
}
