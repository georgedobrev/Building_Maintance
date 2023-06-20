package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.web.exception.InvalidNotificationException;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.service.NotificationService;
import com.blankfactor.MaintainMe.web.resource.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/all")
    public List<Notification> getNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("building/{buildingId}")
    public List<Notification> getNotificationsByBuildingId(@PathVariable Long buildingId) {
        return notificationService.getAllNotificationsByBuilding(buildingId);
    }

    @PostMapping("/sendNotification")
    public ResponseEntity<Notification> authenticate(@RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.sendNotification(request));
    }

    @ExceptionHandler(InvalidNotificationException.class)
    public ResponseEntity<String> handleInvalidNotificationException(InvalidNotificationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
