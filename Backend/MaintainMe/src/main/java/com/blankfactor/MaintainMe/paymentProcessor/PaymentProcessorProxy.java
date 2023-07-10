package com.blankfactor.MaintainMe.paymentProcessor;

import com.blankfactor.MaintainMe.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor 
@Primary
public class PaymentProcessorProxy implements PaymentProcessor{

    private PaymentProcessor paymentProcessor;
    @Override

    public Payment makePayment(Payment payment) {
        return paymentProcessor.makePayment(payment);
    }
}
