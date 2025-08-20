package com.student.dao;

import com.base.BaseDAO;
import com.student.model.Student;

import java.util.List;

public interface StudentDAO extends BaseDAO<Student> {
    List<Student> findAllByYearLevel(String yearLevel);
    List<Student> findAllByProgram(String program);
}
