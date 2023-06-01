package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "payment_details")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "paypal_details_id")
    private PaypalDetails paypalDetails;

    @OneToOne
    @JoinColumn(name = "stripe_details_id")
    private StripeDetails stripeDetails;

}