package com.task.nyl.studentManagement.app.entity;

import javax.persistence.*;

@Entity(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue
    private long id;

    private String courseId;

    private String courseName;

    @ManyToOne
    @JoinColumn(name = "students_id")
    private StudentEntity studentEntity;
}
