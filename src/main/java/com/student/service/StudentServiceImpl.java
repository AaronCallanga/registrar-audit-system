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
    public Student enrollStudent(String name, String yearLevel, String program, String contact) {
        Student student = Student.builder()
                                 .studentNumber(UUID.randomUUID().toString())
                                 .name(name)
                                 .yearLevel(yearLevel)
                                 .program(program)
                                 .contact(contact)
                                 .build();
        return studentDAO.save(student)
                         .orElseThrow(() -> new EntityPersistenceException("Failed to enroll student."));
    }

    @Override
    public Student updateStudentInfo(Long studentId, String name, String yearLevel, String program, String contact) {
        Student student = Student.builder()
                                 .name(name)
                                 .yearLevel(yearLevel)
                                 .program(program)
                                 .contact(contact)
                                 .build();
        return studentDAO.updateById(studentId, student)
                .orElseThrow(() -> new EntityPersistenceException("Failed to update student."));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    public List<Student> getAllStudentsByYearLevel(String yearLevel) {
        return studentDAO.findAllByYearLevel(yearLevel);
    }

    @Override
    public List<Student> getAllStudentsByProgram(String program) {
        return studentDAO.findAllByProgram(program);
    }
}
