package com.documents.service;

import com.base.BaseService;
import com.documents.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService extends BaseService<Document> {
    List<Document> getOngoingRequests();
    List<Document> getCompletedRequests();

    void releaseDocument(Long id);
}
