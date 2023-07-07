package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Payment;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.PaymentRepository;
import com.blankfactor.MaintainMe.service.PaymentService;
import com.blankfactor.MaintainMe.web.resource.PaymentRequest;
import lombok.AllArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
@EnableScheduling
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequest request) throws Exception {
        return ResponseEntity.ok(paymentService.makePayment(request));
    }

//    @GetMapping("/history")
//    public List<Payment> paymentHistory() {
//        return paymentService.getPaymentHistory();
//    }

    @Scheduled(cron = "0 0 0 15 * *")
    public void autoPayment() {
        paymentService.autoPayment();
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void scheduleFixedDelayTask() {
//        List<Payment> aa = paymentRepository.findAll();
//        System.out.println(aa.size());
        paymentService.exportWithoutStream();
    }






}



