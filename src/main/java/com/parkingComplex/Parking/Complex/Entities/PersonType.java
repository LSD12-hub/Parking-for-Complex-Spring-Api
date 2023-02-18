package com.parkingComplex.Parking.Complex.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "persontypes")
public class PersonType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persontypeid")
    @JsonIgnore
    private Long personTypeID;

    @Column(name = "persontype")
    private String personType;

    public PersonType() {}

    public PersonType(Long personTypeID, String personType) {
        this.personTypeID = personTypeID;
        this.personType = personType;
    }

    public Long getPersonTypeID() {
        return personTypeID;
    }

    public void setPersonTypeID(Long personTypeID) {
        this.personTypeID = personTypeID;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
}
