package com.student.service;

import com.documents.model.Document;
import com.student.model.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
    Student enrollStudent(String name, String yearLevel, String program, String contact);
    Document updateStudentInfo(String name, String yearLevel, String program, String contact);
    
    List<Student> getAllStudents();
    List<Student> getAllStudentsByYearLevel(String yearLevel);
    List<Student> getAllStudentsByProgram(String program);
}
