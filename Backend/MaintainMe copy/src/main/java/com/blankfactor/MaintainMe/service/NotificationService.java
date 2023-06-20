package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.web.resource.NotificationRequest;
import com.blankfactor.MaintainMe.web.exception.InvalidNotificationException;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.entity.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public List<Notification> getAllNotificationsByBuilding(Long id){
        return notificationRepository.getNotificationByBuildingId(id);
    }

    public Notification sendNotification(NotificationRequest notificationRequest) {

        try {
            var notification = Notification.builder()
                    .messageTitle(notificationRequest.getMessageTitle())
                    .information(notificationRequest.getInformation())
                    .date(notificationRequest.getDate())
                    .buildingId(notificationRequest.getBuildingId())
                    .build();

            notificationRepository.save(notification);

        }catch (Exception ex){
            throw new InvalidNotificationException(ex.getMessage());
        }

        return null;
    }
}
    




