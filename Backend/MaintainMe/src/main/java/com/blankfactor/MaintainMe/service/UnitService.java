package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.UnitRepository;
import com.blankfactor.MaintainMe.web.resource.UpdateUnitResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getUnitsByUser(User user) {
        return unitRepository.findByUsers(user);
    }

    public List<Unit> getUnits() {
        return unitRepository.findAll();
    }

    public Unit createUnit(Unit unit) {

      return unitRepository.save(unit);

    }

    public List<Unit> getUnitsByBuildingId(Long buildingId) {
        List<Unit> units = unitRepository.findByBuildingId(buildingId);

        return units;
    }

    public Unit updateUnit(Long id, UpdateUnitResource updateUnitResource) {
        Unit existingUnit = unitRepository.findById(id).orElse(null);
        if (existingUnit == null) {
            return null;
        }

        // Update the modifiable properties
        existingUnit.setUnitNumber(updateUnitResource.getUnitNumber());
        existingUnit.setInvoiceAmount(updateUnitResource.getInvoiceAmount());
        existingUnit.setSqm(updateUnitResource.getSqm());
        existingUnit.setIdealSqm(updateUnitResource.getIdealSqm());
        existingUnit.setResidents(updateUnitResource.getResidents());
        existingUnit.setTaxablePets(updateUnitResource.getTaxablePets());
        existingUnit.setOwners(updateUnitResource.getOwners());

        return unitRepository.save(existingUnit);
    }

}

