package com.task.nyl.studentManagement.app.service;

import com.task.nyl.studentManagement.app.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO getStudentByEmail(String email);
    StudentDTO getStudentByStudentId(String studentId);
    StudentDTO updateStudent(String studentId, StudentDTO studentDTO);
    void deleteStudent(String studentId);
    List<StudentDTO> getStudents(int page, int limit);
}
