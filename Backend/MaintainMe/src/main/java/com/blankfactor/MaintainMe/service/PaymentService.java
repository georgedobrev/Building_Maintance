package com.blankfactor.MaintainMe.service;


import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.Payment;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.InvoiceRepository;
import com.blankfactor.MaintainMe.repository.PaymentRepository;
import com.blankfactor.MaintainMe.web.resource.PaymentRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final EmailService emailService;

    public Payment makePayment(PaymentRequest paymentRequest) throws MessagingException, UnsupportedEncodingException {

        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Date date = new Date();

        Invoice invoice = invoiceRepository.getInvoiceById(paymentRequest.getInvoiceId());
        invoice.setTotalAmount(invoice.getTotalAmount()-paymentRequest.getPayedAmount());

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






}
