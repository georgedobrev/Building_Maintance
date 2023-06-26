package com.blankfactor.MaintainMe.web.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUnitResource {


    private Integer unitNumber;
    private Float invoiceAmount;
    private Float sqm;
    private Float idealSqm;
    private Integer residents;
    private Integer taxablePets;

}
