package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.Payment;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.InvoiceRepository;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.PaymentRepository;
import com.blankfactor.MaintainMe.web.resource.PaymentRequest;
import com.sun.java.accessibility.util.EventID;
import lombok.AllArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@EnableScheduling

public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final EmailService emailService;
    private final LocalUserRepository userRepository;

    public Payment makePayment(PaymentRequest paymentRequest) throws Exception {

        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Date date = new Date();

        Invoice invoice = invoiceRepository.getInvoiceById(paymentRequest.getInvoiceId());

        if(paymentRequest.getPayedAmount() >0){
            invoice.setTotalAmount(invoice.getTotalAmount()-paymentRequest.getPayedAmount());
            if(invoice.getTotalAmount() ==0){
                invoice.setIsFullyPaid(true);
            }
        }else {
            throw new Exception("Payed amount should be more than 0");
        }

        invoiceRepository.save(invoice);

        try {

            var payment = Payment.builder()
                    .paymentAmount(paymentRequest.getPayedAmount())
                    .user(authUser)
                    .invoice(invoice)
                    .date(date)
                    .build();

            paymentRepository.save(payment);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String emailSubject = "Payment was made to invoice with ID: " + invoice.getId();
        String emailText = emailSubject + "\n Invoice info: \n Total left: " +invoice.getTotalAmount() + "\n Due date: " + invoice.getDueDate()
                + "\n Invoice info: " + invoice.getInvoiceInfo();

        emailService.sendEmail(authUser.getEmail(),emailSubject, emailText);

        return null;
    }

    public List<Payment> getPaymentHistory(){

        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(authUser.getEmail());
        return paymentRepository.findAllByUserId(authUser.getId());

    }

    public User getAuthUser(){
        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println("email: " + authUser.getEmail());
        return authUser;

    }

    //auto paying


    public void autoPayment() {
        //get all unpaid invoices
        //pay them

            // Rest of your code
        } 
    }


    //  }














