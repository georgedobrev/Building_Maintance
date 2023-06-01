package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "stripe_details")
public class StripeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stripe_id", nullable = false)
    private String stripeID;

    @OneToOne(mappedBy = "stripeDetails")
    private PaymentDetails paymentDetails;

}