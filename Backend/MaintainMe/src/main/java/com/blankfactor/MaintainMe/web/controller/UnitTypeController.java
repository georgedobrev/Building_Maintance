package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.UnitType;
import com.blankfactor.MaintainMe.repository.UnitTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UnitTypeController {
    private final UnitTypeRepository unitTypeRepository;

    @Autowired
    public UnitTypeController(UnitTypeRepository unitTypeRepository) {
        this.unitTypeRepository = unitTypeRepository;
    }


    @GetMapping("/unit-types")
    public List<UnitType> getUnitTypes() {
        return unitTypeRepository.findAll();
    }


    }

