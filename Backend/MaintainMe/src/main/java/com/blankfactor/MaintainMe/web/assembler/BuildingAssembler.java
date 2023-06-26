package com.blankfactor.MaintainMe.web.assembler;

import com.blankfactor.MaintainMe.entity.Address;
import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.web.resource.AddressResource;
import com.blankfactor.MaintainMe.web.resource.BuildingResource;
import org.springframework.stereotype.Component;

@Component
public class BuildingAssembler {

    public Building fromResource(BuildingResource buildingResource) {
        Building building = new Building();
        building.setName(buildingResource.getName());
        building.setFloors(buildingResource.getFloors());
        building.setEntrances(buildingResource.getEntrances());

        AddressAssembler addressAssembler = new AddressAssembler();
        Address address = addressAssembler.fromResource(buildingResource.getAddress());
        building.setAddress(address);

        return building;
    }


    public BuildingResource toResource(Building building) {
        Address address = building.getAddress();

        return BuildingResource.builder()
                .id(building.getId())
                .name(building.getName())
                .address(AddressResource.builder()
                        .id(address.getId())
                        .country(address.getCountry())
                        .region(address.getRegion())
                        .city(address.getCity())
                        .district(address.getDistrict())
                        .postalCode(address.getPostalCode())
                        .streetName(address.getStreetName())
                        .streetNumber(address.getStreetNumber())
                        .build())
                .floors(building.getFloors())
                .entrances(building.getEntrances())
                .build();
    }
}
