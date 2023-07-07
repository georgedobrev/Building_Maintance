package com.blankfactor.MaintainMe.paymentProcessor;

import com.blankfactor.MaintainMe.entity.Payment;

public interface PaymentProcessorInterface {

    public Payment makePayment(Payment payment);
}
