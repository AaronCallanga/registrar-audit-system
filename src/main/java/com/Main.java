package com;

import com.documents.dao.DocumentImplDAO;
import com.documents.model.Document;
import com.documents.service.DocumentService;
import com.documents.service.DocumentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DocumentService documentService = new DocumentServiceImpl(new DocumentImplDAO());

    public static void main(String[] args) {
        documentService.setUpTable();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> createRequest();
                case 2 -> viewRequestById();
                case 3 -> updateRequest();
                case 4 -> deleteRequest();
                case 5 -> viewAllRequests();
                case 6 -> viewOngoingRequests();
                case 7 -> viewCompletedRequests();
                case 8 -> releaseDocument();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    private static void printMenu() {
        System.out.println("\n=== Document Audit Management ===");
        System.out.println("1. Create New Request");
        System.out.println("2. View Request by ID");
        System.out.println("3. Update Request");
        System.out.println("4. Delete Request");
        System.out.println("5. View All Requests");
        System.out.println("6. View Ongoing Requests");
        System.out.println("7. View Completed Requests");
        System.out.println("8. Release Document");
        System.out.println("0. Exit");
        System.out.println("================================");
    }

    private static void createRequest() {
        System.out.println("\n--- Create New Request ---");
        String type = getStringInput("Enter document type: ");
        String requestedBy = getStringInput("Enter requested by: ");
        String contact = getStringInput("Enter contact: ");

        try {
            Document doc = documentService.createRequest(type, requestedBy, contact);
            System.out.println("Request created successfully: " + doc);
        } catch (Exception e) {
            System.out.println("Error creating request: " + e.getMessage());
        }
    }

    private static void viewRequestById() {
        Long id = getLongInput("Enter request ID: ");
        try {
            Document doc = documentService.getRequestById(id);
            System.out.println("Found: " + doc);
        } catch (Exception e) {
            System.out.println("Error fetching request: " + e.getMessage());
        }
    }

    private static void updateRequest() {
        System.out.println("\n--- Update Request ---");
        Long id = getLongInput("Enter request ID: ");
        String type = getStringInput("Enter new document type: ");
        String requestedBy = getStringInput("Enter new requested by: ");
        String contact = getStringInput("Enter new contact: ");

        try {
            Document doc = documentService.updateRequest(id, type, requestedBy, contact);
            System.out.println("Request updated: " + doc);
        } catch (Exception e) {
            System.out.println("Error updating request: " + e.getMessage());
        }
    }

    private static void deleteRequest() {
        Long id = getLongInput("Enter request ID: ");
        try {
            documentService.deleteRequestById(id);
            System.out.println("Request deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting request: " + e.getMessage());
        }
    }

    private static void viewAllRequests() {
        System.out.println("\n--- All Requests ---");
        List<Document> docs = documentService.getAllRequest();
        docs.forEach(System.out::println);
    }

    private static void viewOngoingRequests() {
        System.out.println("\n--- Ongoing Requests ---");
        List<Document> docs = documentService.getOngoingRequests();
        docs.forEach(System.out::println);
    }

    private static void viewCompletedRequests() {
        System.out.println("\n--- Completed Requests ---");
        List<Document> docs = documentService.getCompletedRequests();
        docs.forEach(System.out::println);
    }

    private static void releaseDocument() {
        Long id = getLongInput("Enter request ID to release: ");
        try {
            documentService.releaseDocument(id);
            System.out.println("Document released successfully.");
        } catch (Exception e) {
            System.out.println("Error releasing document: " + e.getMessage());
        }
    }

    // ==========================
    // Utility methods
    // ==========================

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    private static Long getLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID, try again.");
            }
        }
    }
}
