package com.enrollment.service;

import com.exceptions.EntityPersistenceException;
import com.exceptions.ResourceNotFoundException;
import com.enrollment.dao.StudentDAO;
import com.enrollment.model.Student;

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
                                 .studentNumber("STU-" + UUID.randomUUID().toString().replace("-", "").substring(0, 10))
                                 .name(name)
                                 .yearLevel(yearLevel)
                                 .program(program)
                                 .contact(contact)
                                 .build();
        return studentDAO.save(student)
                         .orElseThrow(() -> new EntityPersistenceException("Failed to enroll student."));
    }

    @Override
    public Student updateStudentInfo(Long studentId, String name, String program, String contact) {
        Student existingStudent = studentDAO.findById(studentId)
                                            .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));

        existingStudent.setName(name);
        existingStudent.setProgram(program);
        existingStudent.setContact(contact);

        return studentDAO.updateById(studentId, existingStudent)
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
