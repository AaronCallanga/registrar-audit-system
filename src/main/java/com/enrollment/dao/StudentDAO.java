package com.enrollment.dao;

import com.base.BaseDAO;
import com.enrollment.model.Student;

import java.util.List;

public interface StudentDAO extends BaseDAO<Student> {
    List<Student> findAllByYearLevel(String yearLevel);
    List<Student> findAllByProgram(String program);
}
