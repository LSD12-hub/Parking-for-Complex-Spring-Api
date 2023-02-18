package com.parkingComplex.Parking.Complex.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="cars")
public class CarWithUser {

    @Id
    @Column(name = "numberplate")
    public String numberPlate;
    public String make;
    public String model;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "userid")
    public User user;

    public CarWithUser() {}

    public CarWithUser(String numberPlate, String make, String model, User user) {
        this.numberPlate = numberPlate;
        this.make = make;
        this.model = model;
        this.user = user;
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

    public User getUserID() {
        return user;
    }

    public void setUserID(User user) {
        this.user = user;
    }
}
