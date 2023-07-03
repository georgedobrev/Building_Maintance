package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
}
