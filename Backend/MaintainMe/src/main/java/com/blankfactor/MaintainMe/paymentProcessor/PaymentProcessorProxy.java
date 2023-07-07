package com.blankfactor.MaintainMe.paymentProcessor;

import com.blankfactor.MaintainMe.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProcessorProxy implements PaymentProcessorInterface{

    private final PaymentProcessor paymentProcessor;
    @Override
    public Payment makePayment(Payment payment) {
        return paymentProcessor.makePayment(payment);
    }
}
