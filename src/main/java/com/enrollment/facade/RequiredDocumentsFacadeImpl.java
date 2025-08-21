package com.enrollment.facade;

import com.enrollment.model.RequiredDocument;
import com.enrollment.service.RequiredDocumentService;
import com.util.UserInputUtil;

import java.util.List;

public class RequiredDocumentsFacadeImpl implements RequiredDocumentsFacade {

    private final RequiredDocumentService requiredDocumentService;

    public RequiredDocumentsFacadeImpl(RequiredDocumentService requiredDocumentService) {
        this.requiredDocumentService = requiredDocumentService;
    }

    @Override
    public void viewRequiredDocumentsByStudentId() {
        Long studentId = UserInputUtil.getLongInput("Enter student ID: ");
        try {
            List<RequiredDocument> documents = requiredDocumentService.getRequiredDocumentsByStudentId(studentId);
            System.out.println("\n--- Required Documents for Student ID: " + studentId + " ---");
            if (documents.isEmpty()) {
                System.out.println("No documents found.");
            } else {
                documents.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching documents: " + e.getMessage());
        }
    }

    @Override
    public void viewSubmittedRequiredDocumentsByStudentId() {
        Long studentId = UserInputUtil.getLongInput("Enter student ID: ");
        try {
            List<RequiredDocument> submittedDocs =
                    requiredDocumentService.getRequiredDocumentsByStudentIdAndStatus(studentId, "SUBMITTED");
            System.out.println("\n--- Submitted Documents ---");
            if (submittedDocs.isEmpty()) {
                System.out.println("No submitted documents yet.");
            } else {
                submittedDocs.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching submitted documents: " + e.getMessage());
        }
    }

    @Override
    public void viewMissingRequiredDocumentsByStudentId() {
        Long studentId = UserInputUtil.getLongInput("Enter student ID: ");
        try {
            List<RequiredDocument> missingDocs =
                    requiredDocumentService.getRequiredDocumentsByStudentIdAndStatus(studentId, "MISSING");
            System.out.println("\n--- Missing Documents ---");
            if (missingDocs.isEmpty()) {
                System.out.println("No missing documents. All documents submitted.");
            } else {
                missingDocs.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching missing documents: " + e.getMessage());
        }
    }

    @Override
    public void submitRequiredDocument() {
        Long studentId = UserInputUtil.getLongInput("Enter student ID: ");
        String documentType = UserInputUtil.getStringInput("Enter document type to submit: ");

        try {
            List<RequiredDocument> requiredDocuments = requiredDocumentService.getRequiredDocumentsByStudentId(studentId);
            boolean isDocumentRequired = requiredDocuments.stream()
                                                          .anyMatch(requiredDocument ->
                                                                  requiredDocument.getDocumentType().equalsIgnoreCase(documentType)
                                                                    );
            if (isDocumentRequired) {
                requiredDocumentService.submitRequiredDocument(studentId, documentType);
                System.out.println("Document '" + documentType + "' submitted successfully for student ID: " + studentId);
            } else {
                System.out.println("Document '" + documentType + "' is not required for student ID: " + studentId);
            }

        } catch (Exception e) {
            System.out.println("Error submitting document: " + e.getMessage());
        }
    }
}
