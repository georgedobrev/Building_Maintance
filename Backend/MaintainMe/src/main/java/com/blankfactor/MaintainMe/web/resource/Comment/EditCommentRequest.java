package com.blankfactor.MaintainMe.web.resource.Comment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCommentRequest {

    private String token;

    @NotBlank(message = "comment information cannot be empty.")
    @NotEmpty
    private String text;

}