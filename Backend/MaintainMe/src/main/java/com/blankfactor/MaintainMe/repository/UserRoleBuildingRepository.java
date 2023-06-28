package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.entity.UserRoleBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserRoleBuildingRepository extends JpaRepository<UserRoleBuilding,Long> {

    @Query("SELECT new map(b.id AS buildingId, b.name AS buildingName) " +
            "FROM Building b " +
            "INNER JOIN UserRoleBuilding urb ON b.id = urb.building.id " +
            "WHERE urb.user.id = :userId AND urb.role.id = :roleId")
    Collection<Map<String, Object>> getBuildingDataByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Query("SELECT urb.role.id AS roleId, urb.building.name AS buildingName FROM UserRoleBuilding urb WHERE urb.user.id = ?1")
    Map<String, Object> findRoleAndBuildingByUserId(Long userId);

    @Query(value = "SELECT u.email " +
            "FROM UserRoleBuilding urb " +
            "INNER JOIN User u ON urb.user.id = u.id " +
            "WHERE urb.building.id = :buildingId")
    List<String> getUsersByBuildingId(@Param("buildingId") Long buildingId);

}











