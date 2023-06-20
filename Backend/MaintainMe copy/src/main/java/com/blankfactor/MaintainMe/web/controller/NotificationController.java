package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.service.NotificationService;
import com.blankfactor.MaintainMe.web.exception.InvalidNotificationException;
import com.blankfactor.MaintainMe.web.resource.NotificationByBuildingRequest;
import com.blankfactor.MaintainMe.web.resource.NotificationDeleteRequest;
import com.blankfactor.MaintainMe.web.resource.NotificationEditRequest;
import com.blankfactor.MaintainMe.web.resource.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

//    @GetMapping("/all")
//    public List<Notification> getNotifications(){
//        return notificationService.getAllNotifications();
//    }

    @GetMapping("/building")
    public List<Notification> getNotificationsByBuildingId(@RequestBody NotificationByBuildingRequest request) {
        return notificationService.getAllNotificationsByBuilding(request);
    }

//    @GetMapping("building/{buildingId}")
//    public List<Notification> getNotificationsByBuildingId(@PathVariable Long buildingId) {
//        return notificationService.getAllNotificationsByBuilding(buildingId);
//    }

    @PostMapping("/sendNotification")
    public ResponseEntity<Notification> sendNotification( @RequestBody NotificationRequest request) throws Exception {
        return ResponseEntity.ok(notificationService.sendNotification(request));
    }

    @PostMapping("/editNotification")
    public ResponseEntity<Notification> editNotification(@RequestBody NotificationEditRequest request) throws Exception {
        return ResponseEntity.ok(notificationService.editNotification(request));
    }

    @PostMapping("/deleteNotification")
    public ResponseEntity<Notification> deleteNotification(@RequestBody NotificationDeleteRequest request) {
        return ResponseEntity.ok(notificationService.deleteNotification(request));
    }


    @ExceptionHandler(InvalidNotificationException.class)
    public ResponseEntity<String> handleInvalidNotificationException(InvalidNotificationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}