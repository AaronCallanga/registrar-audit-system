package com.student.mapper;

import com.student.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    public static Student mapResultSetToStudent(ResultSet rs) throws Exception {
        return Student.builder()
                .id(rs.getLong("id"))
                .studentNumber(rs.getString("student_number"))
                .name(rs.getString("name"))
                .yearLevel(rs.getString("year_level"))
                .program(rs.getString("program"))
                .contact(rs.getString("contact"))
                .build();
    }
    public static List<Student> mapResultSetToStudentList(ResultSet rs) throws Exception {
        List<Student> studentList = new ArrayList<>();
        while (rs.next()) {
            studentList.add(mapResultSetToStudent(rs));
        }
        return studentList;
    }

}
