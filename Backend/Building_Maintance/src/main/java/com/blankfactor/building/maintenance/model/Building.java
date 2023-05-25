package com.blankfactor.building.maintenance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "building_name", nullable = false)
    private String buildingName;

    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @OneToMany(mappedBy = "building", orphanRemoval = true)
    private List<Apartment> apartments = new ArrayList<>();

}