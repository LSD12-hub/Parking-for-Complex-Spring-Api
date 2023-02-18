package com.parkingComplex.Parking.Complex.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="cars")
public class Car {


    @Id
    @Column(name = "numberplate")
    private String numberPlate;

    private String make;

    private String model;

    @Column(name="userid")
    @JsonIgnore
    private Long userID;

    public Car () {}

    public Car(String numberPlate, String make, String model, Long userID) {
        this.numberPlate = numberPlate;
        this.make = make;
        this.model = model;
        this.userID = userID;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
