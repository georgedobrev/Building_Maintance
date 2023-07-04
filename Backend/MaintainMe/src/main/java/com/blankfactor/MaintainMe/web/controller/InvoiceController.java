package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

    public InvoiceService invoiceService;

    @Scheduled(cron = "0 0 0 1 * *")
    public void sendInvoice() throws Exception {
        System.out.println("test sch");
         invoiceService.sendMonthlyInvoices();
    }

    @GetMapping("/{id}")
    public List<Invoice> getInvoicesByUnit(@PathVariable("id") Long unitId) {
        return invoiceService.findInvoicesByUnitId(unitId);
    }
    }

