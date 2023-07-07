package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Long> {

    List<Payment> findAllByUserId(Long userId);

    @Transactional(readOnly = true)
    Slice<Payment> findAllBy(Pageable page);
}


