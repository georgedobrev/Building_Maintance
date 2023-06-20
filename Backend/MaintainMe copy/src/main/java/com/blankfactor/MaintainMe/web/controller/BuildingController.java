package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.web.assembler.BuildingAssembler;
import com.blankfactor.MaintainMe.web.resource.BuildingResource;
import com.blankfactor.MaintainMe.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingAssembler buildingAssembler;

    public BuildingController(BuildingService buildingService, BuildingAssembler buildingAssembler) {
        this.buildingService = buildingService;
        this.buildingAssembler = buildingAssembler;
    }

    @GetMapping()
    public ResponseEntity<List<BuildingResource>> getBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        List<BuildingResource> result = new ArrayList<>();

        for (Building building : buildings) {

            result.add(buildingAssembler.toResource(building));
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping()
    public ResponseEntity<BuildingResource> createBuilding(@RequestBody BuildingResource buildingResource) {
        Building createdBuilding = buildingService.createBuilding(buildingAssembler.fromResource(buildingResource));
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingAssembler.toResource(createdBuilding));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingResource> getBuildingById(@PathVariable("id") Long id) {
        Building building = buildingService.getBuildingById(id);

        if (building == null) {
            return ResponseEntity.notFound().build();
        }

        BuildingResource buildingResource = buildingAssembler.toResource(building);
        return ResponseEntity.ok(buildingResource);
    }

    @PostMapping("/{id}")
    public ResponseEntity<BuildingResource> updateBuilding(@PathVariable("id") Long id,
                                                           @RequestBody BuildingResource buildingResource) {
        Building updatedBuilding = buildingService.updateBuilding(id, buildingResource);

        if (updatedBuilding == null) {
            return ResponseEntity.notFound().build(); // Building with the given ID doesn't exist
        }

        BuildingResource updatedBuildingResource = buildingAssembler.toResource(updatedBuilding);
        return ResponseEntity.ok(updatedBuildingResource);
    }
    }







