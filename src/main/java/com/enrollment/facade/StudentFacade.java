package com.enrollment.facade;

import com.enrollment.model.Student;

import java.util.List;

public interface StudentFacade {
    void viewStudentById();
    void removeStudentById();
    void enrollStudent();
    void updateStudentInfo();

    void viewAllStudents();
    void viewAllStudentsByYearLevel();
    void viewAllStudentsByProgram();
}
