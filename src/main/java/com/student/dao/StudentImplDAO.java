package com.student.dao;

import com.student.model.Student;

import java.util.List;
import java.util.Optional;

public class StudentImplDAO implements StudentDAO {

    @Override
    public void createTable() {

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
