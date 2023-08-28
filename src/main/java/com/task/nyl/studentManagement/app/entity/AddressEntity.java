package com.task.nyl.studentManagement.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "addresses")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1048411314640320550L;

    @Id
    @GeneratedValue
    private long id;

    //@Column(nullable = false)
    private String addressId;

   // @Column(nullable = false)
    private String city;


  //  @Column(nullable = false)
    private String streetName;

  //  @Column(nullable = false)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "students_id")
    private StudentEntity studentEntity;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
