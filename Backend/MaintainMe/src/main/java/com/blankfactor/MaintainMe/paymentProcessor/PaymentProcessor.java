package com.blankfactor.MaintainMe.paymentProcessor;

import com.blankfactor.MaintainMe.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor implements PaymentProcessorInterface{

    @Override
    public Payment makePayment(Payment payment) {
        payment.setIsSuccessful(1);
        return payment;
    }
}
