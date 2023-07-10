package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Address;
import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.repository.AddressRepository;
import com.blankfactor.MaintainMe.repository.BuildingRepository;
import com.blankfactor.MaintainMe.web.assembler.BuildingAssembler;
import com.blankfactor.MaintainMe.web.resource.BuildingResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final  AddressRepository addressRepository;

    BuildingAssembler buildingAssembler;


    public BuildingService(BuildingRepository buildingRepository, AddressRepository addressRepository) {
        this.buildingRepository = buildingRepository;
        this.addressRepository = addressRepository;
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Building createBuilding(Building building) {

        addressRepository.save(building.getAddress());
        Building savedBuilding = buildingRepository.save(building);
        return savedBuilding;
    }

    public Building getBuildingById(Long id) {
        return buildingRepository.findById(id).orElse(null);
    }
    @Transactional
    public Building updateBuilding(Long id, BuildingResource buildingResource) {
        Building existingBuilding = buildingRepository.findById(id).orElse(null);
        if (existingBuilding == null) {
            return null;
        }
        existingBuilding.setName(buildingResource.getName());
        existingBuilding.setFloors(buildingResource.getFloors());
        existingBuilding.setEntrances(buildingResource.getEntrances());

        Address existingAddress = existingBuilding.getAddress();
        existingAddress.setCountry(buildingResource.getAddress().getCountry());
        existingAddress.setRegion(buildingResource.getAddress().getRegion());
        existingAddress.setCity(buildingResource.getAddress().getCity());
        existingAddress.setDistrict(buildingResource.getAddress().getDistrict());
        existingAddress.setPostalCode(buildingResource.getAddress().getPostalCode());
        existingAddress.setStreetName(buildingResource.getAddress().getStreetName());
        existingAddress.setStreetNumber(buildingResource.getAddress().getStreetNumber());
        Building savedBuilding = buildingRepository.save(existingBuilding);
        return savedBuilding;
    }

}


