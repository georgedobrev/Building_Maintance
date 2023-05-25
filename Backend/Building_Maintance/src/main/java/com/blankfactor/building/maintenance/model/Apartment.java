package com.blankfactor.building.maintenance.model;

import com.blankfactor.building.maintenance.model.Building;
import com.blankfactor.building.maintenance.model.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "apartment", nullable = false)
    private Integer apartment;

    @OneToMany(mappedBy = "apartment")
    private List<Person> persons = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Person owner;
}