package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.service.NotificationService;
import com.blankfactor.MaintainMe.web.exception.InvalidNotificationException;
import com.blankfactor.MaintainMe.web.resource.Notification.NotificationByBuildingRequest;
import com.blankfactor.MaintainMe.web.resource.Notification.NotificationDeleteRequest;
import com.blankfactor.MaintainMe.web.resource.Notification.NotificationEditRequest;
import com.blankfactor.MaintainMe.web.resource.Notification.NotificationRequest;
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

    @GetMapping("/building")
    public List<Notification> getNotificationsByBuildingId(@RequestBody NotificationByBuildingRequest request) {
        return notificationService.getAllNotificationsByBuilding(request);
    }

    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification( @RequestBody NotificationRequest request) throws Exception {
        return ResponseEntity.ok(notificationService.sendNotification(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<Notification> editNotification(@RequestBody NotificationEditRequest request) throws Exception {
        return ResponseEntity.ok(notificationService.editNotification(request));
    }

    @PostMapping("/delete")
    public ResponseEntity<Notification> deleteNotification(@RequestBody NotificationDeleteRequest request) {
        return ResponseEntity.ok(notificationService.deleteNotification(request));
    }

    @ExceptionHandler(InvalidNotificationException.class)
    public ResponseEntity<String> handleInvalidNotificationException(InvalidNotificationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}