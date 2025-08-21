package com.enrollment.facade;

import com.enrollment.model.Student;
import com.enrollment.service.StudentService;
import com.util.UserInputUtil;

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
    public void enrollStudent(String name, String yearLevel, String program, String contact) {

    }

    @Override
    public void updateStudentInfo(Long studentId, String name, String yearLevel, String program, String contact) {

    }

    @Override
    public void viewAllStudents() {

    }

    @Override
    public void viewAllStudentsByYearLevel(String yearLevel) {

    }

    @Override
    public void viewAllStudentsByProgram(String program) {

    }
}
