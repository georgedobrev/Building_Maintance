package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "unit_type")
public class UnitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "unit_type", nullable = false, length = 20)
    private String unitType;

    @OneToMany(mappedBy = "unitType", orphanRemoval = true)
    private List<Unit> units = new ArrayList<>();



}