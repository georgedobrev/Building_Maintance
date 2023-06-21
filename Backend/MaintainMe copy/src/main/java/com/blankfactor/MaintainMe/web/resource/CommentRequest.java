package com.blankfactor.MaintainMe.web.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class CommentRequest {

    @NotBlank(message = "comment information cannot be empty.")
    @NotEmpty
    private String text;

    private Long userId;

    private Long notificationId;

}