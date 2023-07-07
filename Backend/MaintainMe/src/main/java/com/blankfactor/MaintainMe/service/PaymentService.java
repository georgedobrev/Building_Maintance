package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.Payment;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.paymentProcessor.PaymentProcessorProxy;
import com.blankfactor.MaintainMe.repository.InvoiceRepository;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.PaymentRepository;
import com.blankfactor.MaintainMe.web.resource.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
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
    private final PaymentProcessorProxy paymentProcessorProxy;

    public Payment makePayment(PaymentRequest paymentRequest) {

        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Date date = new Date();

        Invoice invoice = invoiceRepository.getInvoiceById(paymentRequest.getInvoiceId());

        try {

            if(paymentRequest.getPayedAmount() >0 && paymentRequest.getPayedAmount() <= invoice.getTotalAmount() ){

                var payment = Payment.builder()
                        .paymentAmount(paymentRequest.getPayedAmount())
                        .user(authUser)
                        .invoice(invoice)
                        .date(date)
                        .build();

                paymentProcessorProxy.makePayment(payment);
                paymentRepository.save(payment);

                invoice.setTotalAmount(invoice.getTotalAmount() - payment.getPaymentAmount());
                invoiceRepository.save(invoice);

                String emailSubject = "Payment was made to invoice with ID: " + invoice.getId();
                String emailText = emailSubject + "\n Invoice info: \n Total left: " +invoice.getTotalAmount() + "\n Due date: " + invoice.getDueDate()
                        + "\n Invoice info: " + invoice.getInvoiceInfo();

                emailService.sendEmail(authUser.getEmail(),emailSubject, emailText);

                if(invoice.getTotalAmount() ==0){
                    invoice.setIsFullyPaid(true);
                    invoiceRepository.save(invoice);
                }

            }else {
                throw new Exception("Payed amount should be more than 0 or less than total owed");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Payment> getPaymentHistory(){

        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(authUser.getEmail());
        return paymentRepository.findAllByUserId(authUser.getId());
    }

    public void autoPayment() {

        List<User> autoPayUsers = userRepository.getUserByAutoPay();

        for (int i =0; i < autoPayUsers.size(); i++){
            List<Invoice> unpaidInvoices = invoiceRepository.findUnpaidInvoices(autoPayUsers.get(i).getUnit().getId());

            for (int j =0; j<unpaidInvoices.size(); j++){

                Date date = new Date();

                var payment = Payment.builder()
                        .paymentAmount(unpaidInvoices.get(j).getTotalAmount())
                        .user(autoPayUsers.get(i))
                        .invoice(unpaidInvoices.get(j))
                        .date(date)
                        .build();

                paymentProcessorProxy.makePayment(payment);
                paymentRepository.save(payment);

                unpaidInvoices.get(j).setTotalAmount(0F);
                unpaidInvoices.get(j).setIsFullyPaid(true);

                invoiceRepository.save(unpaidInvoices.get(j));
            }
          }
        } 
    }














