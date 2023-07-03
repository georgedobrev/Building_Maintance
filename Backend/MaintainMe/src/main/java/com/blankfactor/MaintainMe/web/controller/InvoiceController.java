package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.service.InvoiceService;
import com.blankfactor.MaintainMe.web.resource.InvoiceResource;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

    public InvoiceService invoiceService;

    @PostMapping("/create")
    public ResponseEntity <Invoice> sendInvoice(@RequestBody InvoiceResource invoiceResource) throws Exception {
      return   ResponseEntity.ok(invoiceService.createInvoice(invoiceResource));
    }

    @GetMapping("/{id}")
    public List<Invoice> getInvoicesByUnit(@PathVariable("id") Long unitId) {
        return invoiceService.findInvoicesByUnitId(unitId);
    }
    }

