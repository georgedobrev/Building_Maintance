package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.Payment;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.InvoiceRepository;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.PaymentRepository;
import com.blankfactor.MaintainMe.web.resource.PaymentRequest;
import com.sun.java.accessibility.util.EventID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

@Service
@AllArgsConstructor
@EnableScheduling
@Slf4j

public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final EmailService emailService;
    private final LocalUserRepository userRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
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

//    public List<Payment> getPaymentHistory(){
//
//        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        return paymentRepository.findAllByUserId(authUser.getId());
//
//    }
    //auto paying

    public void exportWithoutStream() {

        final int PAGE_SIZE = 5;
        int page = 0;
        List<Payment> all = new ArrayList<>();

        Slice<Payment> paymentPage;
        do {
            paymentPage = paymentRepository.findAllByUserId(PageRequest.of(page, PAGE_SIZE), 26L);
            for (Payment payment : paymentPage) {
//                out.println(payment.toString());
                all.add(payment);
            }
            entityManager.clear();
            page++;
        } while (paymentPage.hasNext());

        out.println(all.size());
    }


    @Transactional
    public void autoPayment() {

        //get all users with auto pay

        List<User> autoPayUsers = userRepository.getUserByAutoPay();
        out.println("Users with auto pay:"+ autoPayUsers.size());

        //find invoices per user

        for (int i =0; i < autoPayUsers.size(); i++){
            List<Invoice> unpaidInvoices = invoiceRepository.findUnpaidInvoices(autoPayUsers.get(i).getUnit().getId());
            out.println("All unpaid invoices: " + unpaidInvoices.size());

            //pay them

            for (int j =0; j<unpaidInvoices.size(); j++){

                Date date = new Date();

                var payment = Payment.builder()
                        .paymentAmount(unpaidInvoices.get(j).getTotalAmount())
                        .user(autoPayUsers.get(i))
                        .invoice(unpaidInvoices.get(j))
                        .date(date)
                        .build();

                paymentRepository.save(payment);

                unpaidInvoices.get(j).setTotalAmount(0F);
                unpaidInvoices.get(j).setIsFullyPaid(true);

                invoiceRepository.save(unpaidInvoices.get(j));
            }
        }
        } 
    }














