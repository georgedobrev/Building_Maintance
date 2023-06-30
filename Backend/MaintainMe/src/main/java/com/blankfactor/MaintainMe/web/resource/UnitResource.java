package com.blankfactor.MaintainMe.web.resource;

import com.blankfactor.MaintainMe.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitResource {

    private Long buildingId;
    private Integer unitNumber;
    private Float invoiceAmount;
    private Float sqm;
    private Float idealSqm;
    private Integer residents;
    private Integer taxablePets;
    private Long unitTypeId;
    private List<User> owners;
}
