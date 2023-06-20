package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.service.UnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    private UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<Unit> getUnits(){
        return unitService.getUnits();
    }


}
