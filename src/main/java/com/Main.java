package com;

import com.config.DatabaseConnection;
import com.documents.dao.DocumentRequestImplDAO;
import com.documents.facade.DocumentRequestFacade;
import com.documents.service.DocumentRequestService;
import com.documents.service.DocumentRequestServiceImpl;
import com.documents.facade.DocumentRequestFacadeImpl;
import com.enrollment.dao.RequiredDocumentImplDAO;
import com.enrollment.dao.StudentImplDAO;
import com.enrollment.facade.RequiredDocumentsFacade;
import com.enrollment.facade.RequiredDocumentsFacadeImpl;
import com.enrollment.facade.StudentFacade;
import com.enrollment.facade.StudentFacadeImpl;
import com.enrollment.service.RequiredDocumentService;
import com.enrollment.service.RequiredDocumentServiceImpl;
import com.enrollment.service.StudentService;
import com.enrollment.service.StudentServiceImpl;
import com.util.DisplayUtil;
import com.util.UserInputUtil;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.setUpTables();
        boolean running = true;
        int choice;
        boolean isAuthenticated = false;
        String username = "admin", password = "admin";  // Hardcoded admin user

        while (!isAuthenticated) {
            System.out.println("\n=== Login ===");
            String uname = UserInputUtil.getStringInput("Enter your username: ");
            String pass = UserInputUtil.getStringInput("Enter your password: ");
            
            if (uname.equals(username) && pass.equals(password)) {
                isAuthenticated = true;
                break;
            } else {
                System.out.println("Invalid username or password");
                System.out.println("Please try again");
            }
        }
        
        if (isAuthenticated) {
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

    }

    private static void showEnrollmentDocumentMenu() {
        StudentService studentService = new StudentServiceImpl(new StudentImplDAO());
        RequiredDocumentService requiredDocumentService = new RequiredDocumentServiceImpl(new RequiredDocumentImplDAO());
        StudentFacade studentFacade = new StudentFacadeImpl(studentService, requiredDocumentService);
        RequiredDocumentsFacade requiredDocumentsFacade = new RequiredDocumentsFacadeImpl(requiredDocumentService);
        boolean running = true;

        // FOR ENROLLMENT MENU
        while (running) {
            DisplayUtil.printMenuEnrollmentDocuments();
            int choice = UserInputUtil.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> studentFacade.enrollStudent();
                case 2 -> studentFacade.viewStudentById();
                case 3 -> studentFacade.updateStudentInfo();
                case 4 -> studentFacade.removeStudentById();
                case 5 -> studentFacade.viewAllStudents();
                case 6 -> studentFacade.viewAllStudentsByYearLevel();
                case 7 -> studentFacade.viewAllStudentsByProgram();
                case 8 -> requiredDocumentsFacade.viewRequiredDocumentsByStudentId();
                case 9 -> requiredDocumentsFacade.viewSubmittedRequiredDocumentsByStudentId();
                case 10 -> requiredDocumentsFacade.viewMissingRequiredDocumentsByStudentId();
                case 11 -> requiredDocumentsFacade.submitRequiredDocument();
                case 12 -> {return;}
                case 13 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private static void showDocumentRequestMenu() {
        DocumentRequestService documentService = new DocumentRequestServiceImpl(new DocumentRequestImplDAO());
        DocumentRequestFacade documentFacade = new DocumentRequestFacadeImpl(documentService);
        boolean running = true;

        // FOR DOCUMENT REQUEST MENU
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
}
