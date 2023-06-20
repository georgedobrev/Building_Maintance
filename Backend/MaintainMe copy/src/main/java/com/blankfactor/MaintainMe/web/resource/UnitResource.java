package com.blankfactor.MaintainMe.web.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitResource {
    private Long id;
    private Integer unitNumber;
    private Float invoiceAmount;
    private Float sqm;
    private Float idealSqm;
    private Integer residents;
    private Integer taxablePets;
    private List<RegistrationRequest> users;

}
