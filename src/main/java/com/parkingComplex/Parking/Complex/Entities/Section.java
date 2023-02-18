package com.parkingComplex.Parking.Complex.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="sections")
public class Section {

    @Id
    @Column(name = "sectionid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String section;
    private int capacity;

    public Section() {}

    public Section(Long id, String section, int capacity) {
        this.id = id;
        this.section = section;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
