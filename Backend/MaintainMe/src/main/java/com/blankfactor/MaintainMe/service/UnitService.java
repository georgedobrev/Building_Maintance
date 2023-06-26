package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

}

