package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Comment;
import com.blankfactor.MaintainMe.service.CommentService;
import com.blankfactor.MaintainMe.web.resource.CommentByNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @GetMapping("/notification")
    public List<Comment> getCommentByNotificationId(@RequestBody CommentByNotificationRequest request) {
        return commentService.getCommentByNotificationId(request);
    }
}

