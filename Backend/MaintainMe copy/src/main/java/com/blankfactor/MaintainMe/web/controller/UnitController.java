package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.service.UnitService;
import com.blankfactor.MaintainMe.web.assembler.UnitAssembler;
import com.blankfactor.MaintainMe.web.resource.UnitResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    private UnitService unitService;
    private UnitAssembler unitAssembler;

    public UnitController(UnitService unitService, UnitAssembler unitAssembler) {
        this.unitService = unitService;
        this.unitAssembler = unitAssembler;
    }

    @GetMapping
    public List<Unit> getUnits(){
        return unitService.getUnits();
    }

    @PostMapping()
    public ResponseEntity<UnitResource> createUnit(@RequestBody UnitResource unitResource) {
       Unit createdUnit= unitService.createUnit(unitAssembler.fromResource(unitResource));
        return ResponseEntity.status(HttpStatus.CREATED).body(unitAssembler.toResource(createdUnit));
    }


}
