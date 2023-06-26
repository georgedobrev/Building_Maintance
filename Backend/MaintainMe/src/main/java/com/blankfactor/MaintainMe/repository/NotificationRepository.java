package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationByBuildingId(Long buildingId);


}
