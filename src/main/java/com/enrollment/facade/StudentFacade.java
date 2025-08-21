package com.enrollment.facade;

import com.enrollment.model.Student;

import java.util.List;

public interface StudentFacade {
    void viewStudentById();
    void removeStudentById();
    void enrollStudent(String name, String yearLevel, String program, String contact);
    void updateStudentInfo(Long studentId, String name, String yearLevel, String program, String contact);

    void viewAllStudents();
    void viewAllStudentsByYearLevel(String yearLevel);
    void viewAllStudentsByProgram(String program);
}
