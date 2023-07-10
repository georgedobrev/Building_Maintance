package com.blankfactor.MaintainMe.paymentProcessor;

import com.blankfactor.MaintainMe.entity.Payment;


public interface PaymentProcessor {

    public Payment makePayment(Payment payment);
}
