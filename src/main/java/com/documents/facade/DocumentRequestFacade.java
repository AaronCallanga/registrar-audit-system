package com.documents.facade;

public interface DocumentRequestFacade {
    void createRequest();

    void viewRequestById();

    void updateRequest();

    void deleteRequest();

    void viewAllRequests();

    void viewOngoingRequests();

    void viewCompletedRequests();

    void releaseDocument();
}
