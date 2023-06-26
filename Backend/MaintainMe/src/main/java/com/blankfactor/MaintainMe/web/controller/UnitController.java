package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.service.UnitService;
import com.blankfactor.MaintainMe.web.assembler.UnitAssembler;
import com.blankfactor.MaintainMe.web.resource.UnitResource;
import com.blankfactor.MaintainMe.web.resource.UpdateUnitResource;
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

    @GetMapping("/managed/buildings/{buildingId}/units")
    public List<Unit> getUnitsByBuilding(@PathVariable("buildingId") Long buildingId) {
        return unitService.getUnitsByBuildingId(buildingId);
    }



    @PutMapping("/{id}")
    public ResponseEntity<UnitResource> updateUnit(@PathVariable("id") Long id, @RequestBody UpdateUnitResource updateUnitResource) {
        Unit updatedUnit = unitService.updateUnit(id, updateUnitResource);
        if (updatedUnit == null) {
            return ResponseEntity.notFound().build();
        }
        UnitResource unitResource = unitAssembler.toResource(updatedUnit);
        return ResponseEntity.ok(unitResource);
    }


}
