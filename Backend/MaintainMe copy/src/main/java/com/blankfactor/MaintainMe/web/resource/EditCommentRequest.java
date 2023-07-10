package com.blankfactor.MaintainMe.web.resource;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EditCommentRequest {

    private Long commentId;

    @NotBlank(message = "comment information cannot be empty.")
    @NotEmpty
    private String text;

}