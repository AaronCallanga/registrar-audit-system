package com.student.dao;

import com.config.DatabaseConnection;
import com.student.mapper.StudentMapper;
import com.student.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while creating table students: " + e.getMessage());
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(StudentMapper.mapResultSetToStudent(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Error while finding student: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Student> updateById(Long id, Student obj) {
        String sql = """
                UPDATE students
                SET name = ?, 
                    year_level = ?, 
                    program = ?, 
                    contact = ?
                WHERE id = ?
                RETURNING *
                """;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getYearLevel());
            ps.setString(3, obj.getProgram());
            ps.setString(4, obj.getContact());
            ps.setLong(5, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(StudentMapper.mapResultSetToStudent(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Error while updating student: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error while deleting student: " + e.getMessage());
        }
    }

    @Override
    public Optional<Student> save(Student obj) {
        String sql = """
                INSERT INTO students (student_number, name, year_level, program, contact)
                VALUES (?, ?, ?, ?, ?)
                RETURNING *
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, obj.getStudentNumber());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getYearLevel());
            ps.setString(4, obj.getProgram());
            ps.setString(5, obj.getContact());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(StudentMapper.mapResultSetToStudent(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Error while saving student: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            return StudentMapper.mapResultSetToStudentList(rs);
        } catch (Exception e) {
            System.out.println("Error while finding students: " + e.getMessage());
        }

        return List.of();
    }

    @Override
    public List<Student> findAllByYearLevel(String yearLevel) {
        String sql = "SELECT * FROM students WHERE year_level = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, yearLevel);
            try (ResultSet rs = ps.executeQuery()) {
                return StudentMapper.mapResultSetToStudentList(rs);
            }
        } catch (Exception e) {
            System.out.println("Error while finding students by year level: " + e.getMessage());
        }

        return List.of();
    }

    @Override
    public List<Student> findAllByProgram(String program) {
        String sql = "SELECT * FROM students WHERE program = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, program);
            try (ResultSet rs = ps.executeQuery()) {
                return StudentMapper.mapResultSetToStudentList(rs);
            }

        }catch (Exception e) {
            System.out.println("Error while finding students by program: " + e.getMessage());
        }

        return List.of();
    }
}
