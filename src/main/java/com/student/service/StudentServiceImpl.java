package com.student.service;

import com.exceptions.EntityPersistenceException;
import com.exceptions.ResourceNotFoundException;
import com.student.dao.StudentDAO;
import com.student.model.Student;

import java.util.List;
import java.util.UUID;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));
    }

    @Override
    public void deleteStudentById(Long id) {
        studentDAO.deleteById(id);
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
