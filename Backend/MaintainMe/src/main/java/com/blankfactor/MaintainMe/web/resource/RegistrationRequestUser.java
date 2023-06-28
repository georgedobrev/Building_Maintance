package com.blankfactor.MaintainMe.web.resource;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequestUser {

        @NotBlank
        @Size(min = 6,max = 20)
        private String username;
        @NotBlank
        @Size(min = 8,max = 50)
        @NotBlank
        @Email
        private String email;
        @NotBlank
        @Size(min = 2,max = 32)
        private String firstName;
        @NotBlank
        @Size(min = 2,max = 32)
        private String lastName;

    }
