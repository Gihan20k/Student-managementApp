package com.task.nyl.studentManagement.app.model;

import java.util.List;

public class StudentDetailModel {
    private String firstName;
    private String lastName;
    private String email;
    private List<UserAddresses> addresses;
    private List<CourseModel> courses;

    public List<UserAddresses> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UserAddresses> addresses) {
        this.addresses = addresses;
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

    public List<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
}
