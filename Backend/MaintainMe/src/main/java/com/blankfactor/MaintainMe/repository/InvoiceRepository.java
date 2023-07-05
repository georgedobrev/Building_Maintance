package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Invoice;
import com.blankfactor.MaintainMe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    List<Invoice> findByUnit_Id(Long id);


    @Query(value = "SELECT * FROM invoice u WHERE u.id = :id", nativeQuery = true)
    Invoice getInvoiceById(@Param("id") Long id);

    @Query(value = "SELECT i.* " +
            "FROM invoice i " +
            "JOIN user u ON i.unit_id = u.unit_id " +
            "WHERE u.id = :id AND i.is_fully_paid = 0;", nativeQuery = true)
    List<Invoice> findInvoicesByUserId(@Param("id") Long id);

    @Query(value = "select * from invoice where is_fully_paid =0 and unit_id = :id", nativeQuery = true)
    List<Invoice> findUnpaidInvoices(@Param("id") Long id);









}
