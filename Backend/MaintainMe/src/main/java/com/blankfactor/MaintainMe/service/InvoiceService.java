package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.repository.InvoiceRepository;
import com.blankfactor.MaintainMe.repository.UnitRepository;
import com.blankfactor.MaintainMe.web.exception.InvalidNotificationException;
import com.blankfactor.MaintainMe.web.resource.InvoiceResource;
import org.springframework.jmx.access.InvalidInvocationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;
    private UnitRepository unitRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, UnitRepository unitRepository) {
        this.invoiceRepository = invoiceRepository;
        this.unitRepository = unitRepository;
    }

    public Invoice createInvoice(InvoiceResource invoiceResource) throws Exception {

        Unit unit = unitRepository.findById(invoiceResource.getUnitId()) .
                orElseThrow(() -> new Exception("Unit not found"));

        try{
            var invoice = Invoice.builder()
                    .invoiceInfo(invoiceResource.getInvoiceInfo())
                    .dueDate(invoiceResource.getDueDate())
                    .forMonth(invoiceResource.getForMonth())
                    .isFullyPaid(invoiceResource.getIsFullyPaid())
                    .issueDate(invoiceResource.getIssueDate())
                    .totalAmount(invoiceResource.getTotalAmount())
                    .unit(unit)
                    .build();

            invoiceRepository.save(invoice);

    }catch (Exception ex){
        throw new InvalidInvocationException(ex.getMessage());
    }
      return null;
    }

    public List<Invoice> findInvoicesByUnitId(Long unitId){
       return invoiceRepository.findByUnit_Id(unitId);
    }



}
