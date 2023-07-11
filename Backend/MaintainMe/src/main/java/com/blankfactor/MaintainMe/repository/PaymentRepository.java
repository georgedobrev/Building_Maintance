package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Long> {

    List<Payment> findAllByUserId(Long userId);

    
}
