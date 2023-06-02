package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "floors", nullable = false)
    private Integer floors;

    @Column(name = "entrances")
    private Integer entrances;

    @ManyToMany
    @JoinTable(name = "building_taxes",
            joinColumns = @JoinColumn(name = "building_id"),
            inverseJoinColumns = @JoinColumn(name = "taxes_id"))
    private Set<Tax> taxes = new LinkedHashSet<>();

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "payment_details_id", nullable = false)
    private PaymentDetails paymentDetails;

}