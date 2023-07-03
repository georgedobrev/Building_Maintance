package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    @Query(value = "SELECT * FROM invoice u WHERE u.id = :id", nativeQuery = true)
    Invoice getInvoiceById(@Param("id") Long id);
    
}
