package com.task.nyl.studentManagement.app.dto;

import java.io.Serializable;
import java.util.List;

public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 8745702211587082479L;
    private long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressDTO> addresses;
    private List<CourseDTO> courses;

    public long getId() {
        return id;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
