package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    
}
