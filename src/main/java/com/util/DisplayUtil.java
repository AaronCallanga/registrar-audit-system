package com.util;

public class DisplayUtil {
    public static void printInitialMenu() {
        System.out.println("\n=== Document Audit Management ===");
        System.out.println("[1] Enrollment Document Audit");
        System.out.println("[2] Document Request Audit");
        System.out.println("[3] Exit");
    }
    public static void printMenuDocumentRequest() {
        System.out.println("\n=== Document Request Audit ===");
        System.out.println("1. Create New Request");
        System.out.println("2. View Request by ID");
        System.out.println("3. Update Request");
        System.out.println("4. Delete Request");
        System.out.println("5. View All Requests");
        System.out.println("6. View Ongoing Requests");
        System.out.println("7. View Completed Requests");
        System.out.println("8. Release Document");
        System.out.println("9. Back");
        System.out.println("10. Exit");
        System.out.println("================================");
    }
    public static void printMenuEnrollmentDocuments() {
        System.out.println("=== Enrollment Document Audit ===");
        System.out.println("1. Enroll New Student");
        System.out.println("2. View Student by ID");
        System.out.println("3. Update Student Information");
        System.out.println("4. Remove Student by ID");
        System.out.println("5. View All Students");
        System.out.println("6. View All Students by Year Level");
        System.out.println("7. View All Students by Program");
        System.out.println("--------------------------------");
        System.out.println("8. View Required Documents by Student ID");
        System.out.println("9. View Submitted Documents by Student ID");
        System.out.println("10. View Missing Documents by Student ID");
        System.out.println("11. Submit Required Document");
        System.out.println("12. Back");
        System.out.println("13. Exit");
        System.out.println("================================");
    }
}
