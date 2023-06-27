package com.blankfactor.MaintainMe.web.resource;

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
public class ForgotPasswordEmailRequest {






        @NotBlank(message = "message title cannot be empty.")
        @NotEmpty
        private String email;


}
