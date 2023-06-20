package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.entity.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getUnitsByUser(User user){
        return unitRepository.findByUsers(user);
    }

   public List<Unit> getUnits(){
       return unitRepository.findAll();
   }
}
