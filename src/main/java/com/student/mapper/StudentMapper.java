package com.student.mapper;

import com.student.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper {
    public static Student mapResultSetToRequiredDocument(ResultSet rs) throws Exception {
        return Student.builder()
                .id(rs.getLong("id"))
                .studentNumber(rs.getString("student_number"))
                .name(rs.getString("name"))
                .yearLevel(rs.getString("year_level"))
                .program(rs.getString("program"))
                .contact(rs.getString("contact"))
                .build();
    }


}
