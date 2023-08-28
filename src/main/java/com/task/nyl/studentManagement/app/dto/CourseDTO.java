package com.task.nyl.studentManagement.app.dto;

public class CourseDTO {

    private long id;
    private String courseId;
    private String courseName;
    private StudentDTO studentEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public StudentDTO getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentDTO studentEntity) {
        this.studentEntity = studentEntity;
    }
}
