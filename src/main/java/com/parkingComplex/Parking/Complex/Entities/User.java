package com.parkingComplex.Parking.Complex.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity()
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    @JsonIgnore
    private Long id;
    private String name;
    private String surname;
    @Column(name = "persontypeid")
    private int personTypeID;

    public User() {}

    public User(String name, String surname, int personTypeID) {
        this.name = name;
        this.surname = surname;
        this.personTypeID = personTypeID;
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
}
