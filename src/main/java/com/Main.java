package com;

import com.config.DatabaseConnection;
import com.documents.dao.DocumentImplDAO;
import com.documents.facade.DocumentFacade;
import com.documents.service.DocumentService;
import com.documents.service.DocumentServiceImpl;
import com.documents.facade.DocumentFacadeImpl;
import com.util.DisplayUtil;
import com.util.UserInputUtil;

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
            choice = UserInputUtil.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> showEnrollmentDocumentMenu();
                case 2 -> showDocumentRequestMenu();
                case 3 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showEnrollmentDocumentMenu() {

        // FOR ENROLLMENT MENU
        DisplayUtil.printMenuEnrollmentDocuments();

    }

    private static void showDocumentRequestMenu() {
        DocumentService documentService = new DocumentServiceImpl(new DocumentImplDAO());
        DocumentFacade documentFacade = new DocumentFacadeImpl(documentService);
        boolean running = true;

        while (running) {
            DisplayUtil.printMenuDocumentRequest();
            int choice = UserInputUtil.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> documentFacade.createRequest();
                case 2 -> documentFacade.viewRequestById();
                case 3 -> documentFacade.updateRequest();
                case 4 -> documentFacade.deleteRequest();
                case 5 -> documentFacade.viewAllRequests();
                case 6 -> documentFacade.viewOngoingRequests();
                case 7 -> documentFacade.viewCompletedRequests();
                case 8 -> documentFacade.releaseDocument();
                case 9 -> {return;}
                case 10 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ==========================
    // Utility methods
    // ==========================




}
