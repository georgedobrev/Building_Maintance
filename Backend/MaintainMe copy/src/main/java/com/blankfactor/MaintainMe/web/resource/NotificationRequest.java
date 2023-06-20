package com.blankfactor.MaintainMe.web.resource;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {


        @NotBlank(message = "message title cannot be empty.")
        private String messageTitle;

        @NotBlank(message = "message information cannot be empty.")
        private String information;

        private Date date;

        private Long buildingId;



}
