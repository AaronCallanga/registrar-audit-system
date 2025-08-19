package com.student.service;

import com.student.model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public Student getStudentById(Long id) {
        return null;
    }

    @Override
    public void deleteStudentById(Long id) {

    }

    @Override
    public Student enrollStudent(String name, String yearLevel, String program, String contact) {
        return null;
    }

    @Override
    public Student updateStudentInfo(String name, String yearLevel, String program, String contact) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return List.of();
    }

    @Override
    public List<Student> getAllStudentsByYearLevel(String yearLevel) {
        return List.of();
    }

    @Override
    public List<Student> getAllStudentsByProgram(String program) {
        return List.of();
    }
}
