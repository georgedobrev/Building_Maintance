package com.blankfactor.MaintainMe.service;


import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.BuildingRepository;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.NotificationRepository;
import com.blankfactor.MaintainMe.web.exception.InvalidNotificationException;
import com.blankfactor.MaintainMe.web.resource.NotificationByBuildingRequest;
import com.blankfactor.MaintainMe.web.resource.NotificationDeleteRequest;
import com.blankfactor.MaintainMe.web.resource.NotificationEditRequest;
import com.blankfactor.MaintainMe.web.resource.NotificationRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final BuildingRepository buildingRepository;
    private final LocalUserRepository userRepository;




    public List<Notification> getAllNotificationsByBuilding(NotificationByBuildingRequest request){
        return notificationRepository.getNotificationByBuildingId(request.getId());
    }

    public Notification sendNotification(NotificationRequest notificationRequest) throws Exception {

        Building building =  buildingRepository.findById(notificationRequest.getBuildingId())
                .orElseThrow(() -> new Exception("Building not found"));


        User user= userRepository.findById(notificationRequest.getUserId())
                .orElseThrow(() -> new Exception("User not found"));


        try {
            var notification = Notification.builder()
                    .messageTitle(notificationRequest.getMessageTitle())
                    .information(notificationRequest.getInformation())
                    .date(notificationRequest.getDate())
                    .building(building)
                    .user(user)
                    .build();

            notificationRepository.save(notification);

        }catch (Exception ex){
            throw new InvalidNotificationException(ex.getMessage());
        }

        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public Notification editNotification(NotificationEditRequest notificationEditRequest) throws Exception {

        Building building =  buildingRepository.findById(notificationEditRequest.getBuildingId())
                .orElseThrow(() -> new Exception("Building not found"));

        Notification notification = notificationRepository.findById(notificationEditRequest.getId())
                .orElseThrow(() -> new Exception("Notification not found"));

        notification.setInformation(notificationEditRequest.getInformation());
        notification.setMessageTitle(notificationEditRequest.getMessageTitle());
        notification.setBuilding(building);

        notificationRepository.save(notification);

        return null;
    }


    public Notification deleteNotification(NotificationDeleteRequest notificationDeleteRequest) {

        try {
            notificationRepository.deleteById(notificationDeleteRequest.getId());
        }catch (Exception ex){
            throw new InvalidNotificationException(ex.getMessage());
        }

        return null;
    }


}
