package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepository extends ListCrudRepository<Address,Long> {
}
