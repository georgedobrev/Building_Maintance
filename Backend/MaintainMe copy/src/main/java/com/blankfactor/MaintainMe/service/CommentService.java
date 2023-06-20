package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Comment;
import com.blankfactor.MaintainMe.repository.CommentRepository;
import com.blankfactor.MaintainMe.web.resource.CommentByNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getCommentByNotificationId(CommentByNotificationRequest request){
        return commentRepository.getCommentByNotificationId(request.getId());
    }






}
