package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Address;
import com.blankfactor.MaintainMe.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface BuildingRepository extends ListCrudRepository<Building,Long> {
    Building findByAddress(Address address);

}
