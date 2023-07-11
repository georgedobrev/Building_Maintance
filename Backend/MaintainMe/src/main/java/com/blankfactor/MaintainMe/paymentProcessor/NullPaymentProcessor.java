package com.blankfactor.MaintainMe.paymentProcessor;

import com.blankfactor.MaintainMe.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class NullPaymentProcessor implements PaymentProcessor{

    @Override
    public Payment makePayment(Payment payment) {
        payment.setSuccessful(true);
        return payment;
    }
}
