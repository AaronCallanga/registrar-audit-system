package com.student.dao;

import com.student.model.Student;

import java.util.List;
import java.util.Optional;

public class StudentImplDAO implements StudentDAO {

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (\n" +
                "    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
                "    student_number VARCHAR(255) NOT NULL UNIQUE,\n" +
                "    name VARCHAR(255) NOT NULL,\n" +
                "    year_level VARCHAR(255) NOT NULL,\n" +
                "    program VARCHAR(255) NOT NULL,\n" +
                "    contact VARCHAR(255) NOT NULL\n" +
                ");";
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> updateById(Long id, Student obj) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Student> save(Student obj) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }
}
