package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.UserRoleBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Map;

public interface UserRoleBuildingRepository extends JpaRepository<UserRoleBuilding,Long> {

    @Query("SELECT new map(b.id AS buildingId, b.name AS buildingName) " +
            "FROM Building b " +
            "INNER JOIN UserRoleBuilding urb ON b.id = urb.building.id " +
            "WHERE urb.user.id = :userId AND urb.role.id = :roleId")
    Collection<Map<String, Object>> getBuildingDataByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}






    //List<UserRoleBuilding> findByUserAndRole(Optional user, Role role);





