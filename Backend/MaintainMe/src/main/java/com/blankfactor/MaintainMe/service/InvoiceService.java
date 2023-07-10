package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.InvoiceRepository;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.UnitRepository;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private UnitRepository unitRepository;
   private final EmailService emailService;


    private final LocalUserRepository localUserRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, UnitRepository unitRepository, EmailService emailService, LocalUserRepository localUserRepository) {
        this.invoiceRepository = invoiceRepository;
        this.unitRepository = unitRepository;
        this.emailService = emailService;
        this.localUserRepository = localUserRepository;
    }


    public List<Invoice> findInvoicesByUnitId(Long unitId) {
        return invoiceRepository.findByUnit_Id(unitId);
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void sendMonthlyInvoices() throws MessagingException, UnsupportedEncodingException {
        List<Unit> units = unitRepository.findAll();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        Date nextMonthDate = calendar.getTime();
        Date date = new Date();
        User user=new User();

        boolean fullyPaid = false;
        for (Unit unit : units) {
            float invoiceAmount = InvoiceService.calculateTotalAmount(unit);
          List <User> users= localUserRepository.getUsersByUnitId(unit.getId());
            var invoice = Invoice.builder()
                    .invoiceInfo("Your monthly Invoice:")
                    .dueDate(nextMonthDate)
                    .forMonth(date)
                    .isFullyPaid(fullyPaid)
                    .issueDate(date)
                    .totalAmount(invoiceAmount)
                    .unit(unit)
                    .build();

            String invoiceString = "Invoice Information:\n" +
                    "Invoice Info: " + invoice.getInvoiceInfo() + "\n" +
                    "Due Date: " + invoice.getDueDate() + "\n" +
                    "For Month: " + invoice.getForMonth() + "\n" +
                    "Is Fully Paid: " + invoice.getIsFullyPaid() + "\n" +
                    "Issue Date: " + invoice.getIssueDate() + "\n" +
                    "Total Amount: " + invoice.getTotalAmount() + "\n" +
                    "Unit: " + unit.getBuilding().getAddress().toString();

            for (int i =0; i <users.size();i++){
                emailService.sendEmail(users.get(i).getEmail(), invoice.getInvoiceInfo(), invoiceString);
            }

            invoiceRepository.save(invoice);

        }


    }


    public static float calculateTotalAmount(Unit unit) {

        float invoiceAmount = (unit.getSqm() * 1) + (unit.getResidents() * 15) + (unit.getTaxablePets() * 5);
        return invoiceAmount;
    }

}
