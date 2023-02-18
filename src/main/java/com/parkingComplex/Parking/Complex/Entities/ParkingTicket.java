package com.parkingComplex.Parking.Complex.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="parkingtickets")
public class ParkingTicket {

    @Id
    @Column(name="parkingticketid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Column(name="numberplate")
    private String numberPlate;
    @Column(name="sectionid")
    private int sectionid;
    @Column(name="arrivaltime")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date arrivalTime;
    @Column(name="departuretime")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date departureTime;

    public ParkingTicket() {}

    public ParkingTicket(Long id, String numberPlate, int sectionid, Date arrivalTime, Date departureTime) {
        this.id = id;
        this.numberPlate = numberPlate;
        this.sectionid = sectionid;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public int getSectionid() {
        return sectionid;
    }

    public void setSectionid(int sectionid) {
        this.sectionid = sectionid;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
}
