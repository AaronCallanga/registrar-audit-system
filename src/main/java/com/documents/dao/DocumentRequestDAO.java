package com.documents.dao;

import com.base.BaseDAO;
import com.documents.model.Document;

import java.util.List;

public interface DocumentRequestDAO extends BaseDAO<Document> {
    List<Document> findOngoingRequests();
    List<Document> findCompletedRequests();
}
