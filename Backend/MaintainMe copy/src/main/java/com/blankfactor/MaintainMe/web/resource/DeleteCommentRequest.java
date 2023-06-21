package com.blankfactor.MaintainMe.web.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeleteCommentRequest {

    private long commentId;
}