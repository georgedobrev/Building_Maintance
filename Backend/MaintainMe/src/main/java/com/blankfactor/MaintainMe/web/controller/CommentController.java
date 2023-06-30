package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Comment;
import com.blankfactor.MaintainMe.service.CommentService;
import com.blankfactor.MaintainMe.web.exception.InvalidCommentException;
import com.blankfactor.MaintainMe.web.resource.Comment.CommentByNotificationRequest;
import com.blankfactor.MaintainMe.web.resource.Comment.CommentRequest;
import com.blankfactor.MaintainMe.web.resource.Comment.EditCommentRequest;
import com.blankfactor.MaintainMe.web.resource.Notification.DeleteCommentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @GetMapping("/notification")
    public List<Comment> getCommentByNotificationId(@RequestBody CommentByNotificationRequest request) {
        return commentService.getCommentByNotificationId(request);
    }

    @PostMapping("/send")
    public ResponseEntity<Comment> sendComment(@RequestBody CommentRequest request) throws Exception {
        return ResponseEntity.ok(commentService.sendComment(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<Comment> editComment(@RequestBody EditCommentRequest request) throws Exception {
        return ResponseEntity.ok(commentService.editComment(request));
    }

    @PostMapping("/delete")
    public ResponseEntity<Comment> deleteComment(@RequestBody DeleteCommentRequest request) throws Exception {
        return ResponseEntity.ok(commentService.deleteComment(request));
    }

    @ExceptionHandler(InvalidCommentException.class)
    public ResponseEntity<String> handleInvalidCommentException(InvalidCommentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}

