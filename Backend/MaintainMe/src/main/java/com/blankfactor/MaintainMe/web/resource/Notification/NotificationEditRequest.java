package com.blankfactor.MaintainMe.web.resource.Notification;

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
public class NotificationEditRequest {

    private Long id;

    @NotBlank(message = "message title cannot be empty.")
    @NotEmpty
    private String messageTitle;

    @NotBlank(message = "message information cannot be empty.")
    @NotEmpty
    private String information;

    private Long buildingId;

}