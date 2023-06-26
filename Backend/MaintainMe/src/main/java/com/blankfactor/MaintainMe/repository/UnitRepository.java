package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Unit;
import com.blankfactor.MaintainMe.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UnitRepository extends ListCrudRepository<Unit,Long>{
    List<Unit> findByUsers(User users);

}
