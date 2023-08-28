package com.task.nyl.studentManagement.app.dto;

public class AddressDTO {

    private long id;
    private String addressId;
    private String city;
    private String streetName;
    private String postalCode;
    private StudentDTO studentEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public StudentDTO getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentDTO studentDTO) {
        this.studentEntity = studentDTO;
    }

  public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

}
