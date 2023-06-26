package com.blankfactor.MaintainMe.web.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingResource {
    private Long id;
    private String name;
    private AddressResource address;
    private Integer floors;
    private Integer entrances;

}