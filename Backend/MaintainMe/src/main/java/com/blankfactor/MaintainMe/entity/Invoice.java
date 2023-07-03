package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(name = "total_amount", nullable = false)
    private Float totalAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "for_month", nullable = false)
    private Date forMonth;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column(name = "invoice_info", nullable = false)
    private String invoiceInfo;

    @Column(name = "is_fully_paid", nullable = false)
    private Boolean isFullyPaid = false;

}