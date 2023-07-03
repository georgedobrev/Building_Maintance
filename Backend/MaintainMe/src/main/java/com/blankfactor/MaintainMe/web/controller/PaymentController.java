package com.blankfactor.MaintainMe.web.controller;


import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.entity.Payment;
import com.blankfactor.MaintainMe.service.PaymentService;
import com.blankfactor.MaintainMe.web.resource.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequest request) throws Exception {
        return ResponseEntity.ok(paymentService.makePayment(request));
    }
}
