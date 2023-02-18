package com.parkingComplex.Parking.Complex.Entities;

import jakarta.persistence.*;


import java.util.List;

@Entity()
@Table(name="users")
public class UserWithCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;
    private String name;
    private String surname;
    @Column(name = "persontypeid")
    private int personTypeID;

    @OneToMany(mappedBy = "userID")
    public List<Car> cars;

    public UserWithCars() {
    }

    public UserWithCars(Long id, String name, String surname, int personTypeID, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personTypeID = personTypeID;
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPersonTypeID() {
        return personTypeID;
    }

    public void setPersonTypeID(int personTypeID) {
        this.personTypeID = personTypeID;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
