package com.documents.facade;

import com.documents.model.Document;
import com.documents.service.DocumentService;
import com.util.UserInputUtil;

import java.util.List;

public class DocumentFacadeImpl implements DocumentFacade {

    private final DocumentService documentService;

    public DocumentFacadeImpl(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public void createRequest() {
        System.out.println("\n--- Create New Request ---");
        String type = UserInputUtil.getStringInput("Enter document type: ");
        String requestedBy = UserInputUtil.getStringInput("Enter requested by: ");
        String contact = UserInputUtil.getStringInput("Enter contact: ");

        try {
            Document doc = documentService.createRequest(type, requestedBy, contact);
            System.out.println("Request created successfully: " + doc);
        } catch (Exception e) {
            System.out.println("Error creating request: " + e.getMessage());
        }
    }

    @Override
    public void viewRequestById() {
        Long id = UserInputUtil.getLongInput("Enter request ID: ");
        try {
            Document doc = documentService.getRequestById(id);
            System.out.println("Found: " + doc);
        } catch (Exception e) {
            System.out.println("Error fetching request: " + e.getMessage());
        }
    }

    @Override
    public void updateRequest() {
        System.out.println("\n--- Update Request ---");
        Long id = UserInputUtil.getLongInput("Enter request ID: ");
        String type = UserInputUtil.getStringInput("Enter new document type: ");
        String requestedBy = UserInputUtil.getStringInput("Enter new requested by: ");
        String contact = UserInputUtil.getStringInput("Enter new contact: ");

        try {
            Document doc = documentService.updateRequest(id, type, requestedBy, contact);
            System.out.println("Request updated: " + doc);
        } catch (Exception e) {
            System.out.println("Error updating request: " + e.getMessage());
        }
    }

    @Override
    public void deleteRequest() {
        Long id = UserInputUtil.getLongInput("Enter request ID: ");
        try {
            documentService.deleteRequestById(id);
            System.out.println("Request deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting request: " + e.getMessage());
        }
    }

    @Override
    public void viewAllRequests() {
        System.out.println("\n--- All Requests ---");
        List<Document> docs = documentService.getAllRequest();
        docs.forEach(System.out::println);
    }

    @Override
    public void viewOngoingRequests() {
        System.out.println("\n--- Ongoing Requests ---");
        List<Document> docs = documentService.getOngoingRequests();
        docs.forEach(System.out::println);
    }

    @Override
    public void viewCompletedRequests() {
        System.out.println("\n--- Completed Requests ---");
        List<Document> docs = documentService.getCompletedRequests();
        docs.forEach(System.out::println);
    }

    @Override
    public void releaseDocument() {
        Long id = UserInputUtil.getLongInput("Enter request ID to release: ");
        try {
            documentService.releaseDocument(id);
            System.out.println("Document released successfully.");
        } catch (Exception e) {
            System.out.println("Error releasing document: " + e.getMessage());
        }
    }
}
