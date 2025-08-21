package com.enrollment.facade;

import com.enrollment.model.Student;
import com.enrollment.service.StudentService;
import com.util.UserInputUtil;

import java.util.List;

public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void viewStudentById() {
        Long id = UserInputUtil.getLongInput("Enter student ID: ");
        try {
            Student student = studentService.getStudentById(id);
            System.out.println("Found: " + student);
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
        String yearLevel = UserInputUtil.getStringInput("Enter year level: ");
        String program = UserInputUtil.getStringInput("Enter program: ");
        String contact = UserInputUtil.getStringInput("Enter contact: ");

        try {
            Student student = studentService.enrollStudent(name, yearLevel, program, contact);
            System.out.println("Enrolled successfully: " + student);
        } catch (Exception e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    @Override
    public void updateStudentInfo() {
        System.out.println("\n--- Update Student Info ---");
        Long studentId = UserInputUtil.getLongInput("Enter student ID: ");
        String name = UserInputUtil.getStringInput("Enter name: ");
        String yearLevel = UserInputUtil.getStringInput("Enter year level: ");
        String program = UserInputUtil.getStringInput("Enter program: ");
        String contact = UserInputUtil.getStringInput("Enter contact: ");

        try {
            Student updated = studentService.updateStudentInfo(studentId, name, yearLevel, program, contact);
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
        System.out.println("\n--- Students in Year Level: " + yearLevel + " ---");
        List<Student> students = studentService.getAllStudentsByYearLevel(yearLevel);
        students.forEach(System.out::println);
    }

    @Override
    public void viewAllStudentsByProgram() {

    }
}
