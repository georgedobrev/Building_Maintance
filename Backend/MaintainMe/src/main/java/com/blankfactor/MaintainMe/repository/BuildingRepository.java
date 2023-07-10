package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Address;
import com.blankfactor.MaintainMe.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;



public interface BuildingRepository extends JpaRepository<Building,Long> {
    Building findByAddress(Address address);



}
