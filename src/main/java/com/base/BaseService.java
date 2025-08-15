package com.base;

import com.documents.model.Document;

import java.util.List;

public interface BaseService<T> {
    T getById(Long id);
    T createRequest(T request);
    T updateRequest(Long id, T updatedRequest);
    void deleteRequest(Long id);
    List<T> getAllRequests();
}
