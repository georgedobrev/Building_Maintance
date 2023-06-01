package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "street_name", nullable = false)
    private String street_name;

    @Column(name = "street_number", nullable = false)
    private String street_number;


    @OneToOne(mappedBy = "address", optional = false, orphanRemoval = true)
    private Building building;

}