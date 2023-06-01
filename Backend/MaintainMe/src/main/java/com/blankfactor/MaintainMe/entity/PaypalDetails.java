package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "paypal_details")
public class PaypalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "routing_number", nullable = false)
    private String routingNumber;

    @OneToOne(mappedBy = "paypalDetails", orphanRemoval = true)
    private PaymentDetails paymentDetails;

}