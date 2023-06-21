package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.entity.Comment;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.CommentRepository;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.NotificationRepository;
import com.blankfactor.MaintainMe.web.exception.InvalidCommentException;
import com.blankfactor.MaintainMe.web.exception.InvalidNotificationException;
import com.blankfactor.MaintainMe.web.resource.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final LocalUserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public List<Comment> getCommentByNotificationId(CommentByNotificationRequest request){
        return commentRepository.getCommentByNotificationId(request.getId());
    }


    public Comment sendComment(CommentRequest commentRequest) throws Exception {

        User user= userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new Exception("User not found"));

        Notification notification = notificationRepository.findById(commentRequest.getNotificationId())
                .orElseThrow(() -> new Exception("Notification not found"));

        Date date = new Date();

        try {

            var comment = Comment.builder()
                    .text(commentRequest.getText())
                    .user(user)
                    .notification(notification)
                    .date(date)
                    .build();

            commentRepository.save(comment);

        }catch (Exception ex){
            throw new InvalidCommentException(ex.getMessage());
        }

        return null;
    }

    public Comment editComment(EditCommentRequest editCommentRequest) throws Exception{

        Comment comment = commentRepository.findById(editCommentRequest.getCommentId())
                .orElseThrow(() -> new Exception("Notification not found"));


        comment.setText(editCommentRequest.getText());
        commentRepository.save(comment);


        return null;
    }

    public Comment deleteComment(DeleteCommentRequest request) throws Exception{

        try {
            notificationRepository.deleteById(request.getCommentId());
        }catch (Exception ex){
            throw new InvalidCommentException(ex.getMessage());
        }

        return null;
    }
}