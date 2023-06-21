package com.blankfactor.MaintainMe.web.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {


        @NotBlank(message = "message title cannot be empty.")
        @NotEmpty
        private String messageTitle;

        @NotBlank(message = "message information cannot be empty.")
        @NotEmpty
        private String information;

        private Long buildingId;



}
