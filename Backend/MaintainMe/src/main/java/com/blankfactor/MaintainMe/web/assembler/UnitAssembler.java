package com.blankfactor.MaintainMe.web.assembler;

import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.entity.UnitType;
import com.blankfactor.MaintainMe.repository.BuildingRepository;
import com.blankfactor.MaintainMe.repository.UnitTypeRepository;
import com.blankfactor.MaintainMe.web.resource.UnitResource;
import org.springframework.stereotype.Component;

@Component
public class UnitAssembler {

    private final BuildingRepository buildingRepository;
    private final UnitTypeRepository unitTypeRepository;

    public UnitAssembler(BuildingRepository buildingRepository, UnitTypeRepository unitTypeRepository) {
        this.buildingRepository = buildingRepository;
        this.unitTypeRepository = unitTypeRepository;
    }

    public Unit fromResource(UnitResource resource) {
        Unit unit = new Unit();
        unit.setUnitNumber(resource.getUnitNumber());
        unit.setInvoiceAmount(resource.getInvoiceAmount());
        unit.setSqm(resource.getSqm());
        unit.setIdealSqm(resource.getIdealSqm());
        unit.setResidents(resource.getResidents());
        unit.setTaxablePets(resource.getTaxablePets());
        Building building = buildingRepository.getById(resource.getBuildingId());
        UnitType unitType= unitTypeRepository.getById(resource.getUnitTypeId());

        unit.setBuilding(building);
        unit.setUnitType(unitType);

        return unit;
    }

    public UnitResource toResource(Unit unit) {
        UnitResource resource = new UnitResource();
        resource.setBuildingId(unit.getBuilding().getId());
        resource.setUnitNumber(unit.getUnitNumber());
        resource.setInvoiceAmount(unit.getInvoiceAmount());
        resource.setSqm(unit.getSqm());
        resource.setIdealSqm(unit.getIdealSqm());
        resource.setResidents(unit.getResidents());
        resource.setTaxablePets(unit.getTaxablePets());
        resource.setUnitTypeId(unit.getUnitType().getId());


        return resource;
    }
}
