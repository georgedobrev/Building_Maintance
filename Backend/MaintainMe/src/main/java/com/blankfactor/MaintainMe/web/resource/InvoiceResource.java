package com.blankfactor.MaintainMe.web.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InvoiceResource {
    private Long unitId;
    private Float totalAmount;
    private Date dueDate;
    private Date forMonth;
    private Date issueDate;
    private String invoiceInfo;
    private Boolean isFullyPaid;

}
