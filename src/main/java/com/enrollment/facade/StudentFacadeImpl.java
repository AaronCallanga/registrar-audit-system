package com.enrollment.facade;

import com.enrollment.RequiredDocumentsByYearLevel;
import com.enrollment.model.RequiredDocument;
import com.enrollment.model.Student;
import com.enrollment.service.RequiredDocumentService;
import com.enrollment.service.StudentService;
import com.util.UserInputUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final RequiredDocumentService requiredDocumentService;

    public StudentFacadeImpl(StudentService studentService, RequiredDocumentService requiredDocumentService) {
        this.studentService = studentService;
        this.requiredDocumentService = requiredDocumentService;
    }

    @Override
    public void viewStudentById() {
        Long id = UserInputUtil.getLongInput("Enter student ID: ");
        try {
            Student student = studentService.getStudentById(id);
            System.out.println("\n--- Student Info ---");
            System.out.println(student);

            // Fetch required documents for this student
            List<RequiredDocument> documents = requiredDocumentService.getRequiredDocumentsByStudentId(id);
            if (documents.isEmpty()) {
                System.out.println("No required documents found for this student.");
            } else {
                System.out.println("\n--- Required Documents ---");
                documents.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }

    }

    @Override
    public void removeStudentById() {
        Long id = UserInputUtil.getLongInput("Enter student ID to delete: ");
        try {
            studentService.deleteStudentById(id);
            System.out.println("Student deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    @Override
    public void enrollStudent() {
        System.out.println("\n--- Enroll New Student ---");
        String name = UserInputUtil.getStringInput("Enter name: ");
        String yearLevel = UserInputUtil.getStringInput("Enter year level (1, 2, 3, 4): ");
        RequiredDocumentsByYearLevel normalized = RequiredDocumentsByYearLevel.fromInput(yearLevel);
        String program = UserInputUtil.getStringInput("Enter program: ");
        String contact = UserInputUtil.getStringInput("Enter contact: ");

        try {
            Student student = studentService.enrollStudent(name, normalized.name(), program, contact);
            System.out.println("Enrolled successfully: " + student);

            RequiredDocumentsByYearLevel documentsByYearLevel = RequiredDocumentsByYearLevel.fromInput(yearLevel);

            List<RequiredDocument> requiredDocuments = new ArrayList<>();
            for (String doc : documentsByYearLevel.getRequiredDocuments()) {
                RequiredDocument rd = RequiredDocument.builder()
                        .studentId(student.getId())
                        .documentType(doc)
                        .status("MISSING")
                        .build();
                requiredDocuments.add(requiredDocumentService.createRequiredDocuments(rd));
            }

            // Print the generated docs
            if (requiredDocuments.isEmpty()) {
                System.out.println("No required documents yet. You may submit them later.");
            } else {
                System.out.println("\n--- Required Documents ---");
                requiredDocuments.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    @Override
    public void updateStudentInfo() {
        System.out.println("\n--- Update Student Info ---");
        Long studentId = UserInputUtil.getLongInput("Enter student ID: ");
        String name = UserInputUtil.getStringInput("Enter name: ");
        String program = UserInputUtil.getStringInput("Enter program: ");
        String contact = UserInputUtil.getStringInput("Enter contact: ");

        try {
            Student updated = studentService.updateStudentInfo(studentId, name, program, contact);
            System.out.println("Updated successfully: " + updated);
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    @Override
    public void viewAllStudents() {
        System.out.println("\n--- List of Students ---");
        List<Student> students = studentService.getAllStudents();
        students.forEach(System.out::println);
    }

    @Override
    public void viewAllStudentsByYearLevel() {
        String yearLevel = UserInputUtil.getStringInput("Enter year level: ");
        RequiredDocumentsByYearLevel normalized = RequiredDocumentsByYearLevel.fromInput(yearLevel);
        System.out.println("\n--- Students in Year Level: " + yearLevel + " ---");
        List<Student> students = studentService.getAllStudentsByYearLevel(normalized.name());
        students.forEach(System.out::println);
    }

    @Override
    public void viewAllStudentsByProgram() {
        String program = UserInputUtil.getStringInput("Enter program: ");
        System.out.println("\n--- Students in Program: " + program + " ---");
        List<Student> students = studentService.getAllStudentsByProgram(program);
        students.forEach(System.out::println);
    }
}
