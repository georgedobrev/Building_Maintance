package com.blankfactor.MaintainMe.web.assembler;

import com.blankfactor.MaintainMe.entity.Address;
import com.blankfactor.MaintainMe.web.resource.AddressResource;
import com.blankfactor.MaintainMe.web.resource.BuildingResource;
import org.springframework.stereotype.Component;

@Component
public class AddressAssembler {

    public Address fromResource(AddressResource addressResource) {
        Address address = new Address();
        address.setCountry(addressResource.getCountry());
        address.setRegion(addressResource.getRegion());
        address.setCity(addressResource.getCity());
        address.setDistrict(addressResource.getDistrict());
        address.setPostalCode(addressResource.getPostalCode());
        address.setStreetName(addressResource.getStreetName());
        address.setStreetNumber(addressResource.getStreetNumber());

        return address;
    }

    public AddressResource toResource(Address address) {
        AddressResource addressResource = new AddressResource();
        addressResource.setCountry(address.getCountry());
        addressResource.setRegion(address.getRegion());
        addressResource.setCity(address.getCity());
        addressResource.setDistrict(address.getDistrict());
        addressResource.setPostalCode(address.getPostalCode());
        addressResource.setStreetName(address.getStreetName());
        addressResource.setStreetNumber(address.getStreetNumber());

        return addressResource;
    }

}