package com;

import com.config.DatabaseConnection;
import com.documents.dao.DocumentImplDAO;
import com.documents.service.DocumentService;
import com.documents.service.DocumentServiceImpl;
import com.documents.facade.DocumentFacadeImpl;
import com.util.DisplayUtil;
import com.util.ScannerInputUtil;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DocumentService documentService = new DocumentServiceImpl(new DocumentImplDAO());

    public static void main(String[] args) {
        DatabaseConnection.setUpTables();
        boolean running = true;
        int choice;
        while (running) {
            DisplayUtil.printInitialMenu();
            choice = ScannerInputUtil.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    DisplayUtil.printMenuDocumentRequest();
                    break;
                case 2:
                    showDocumentRequestMenu();
                    break;
                case 3:
                    System.out.println("EXITING....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void showDocumentRequestMenu() {
        DocumentService documentService = new DocumentServiceImpl(new DocumentImplDAO());
        DocumentFacadeImpl documentFacade = new DocumentFacadeImpl(documentService);
        boolean running = true;

        while (running) {
            DisplayUtil.printMenuDocumentRequest();
            int choice = ScannerInputUtil.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> createRequest();
                case 2 -> viewRequestById();
                case 3 -> updateRequest();
                case 4 -> deleteRequest();
                case 5 -> viewAllRequests();
                case 6 -> viewOngoingRequests();
                case 7 -> viewCompletedRequests();
                case 8 -> releaseDocument();
                case 9 -> {return;}
                case 10 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ==========================
    // Utility methods
    // ==========================




}
