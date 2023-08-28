package com.task.nyl.studentManagement.app.service;

import com.task.nyl.studentManagement.app.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getCourseByStudent(String id);
}
