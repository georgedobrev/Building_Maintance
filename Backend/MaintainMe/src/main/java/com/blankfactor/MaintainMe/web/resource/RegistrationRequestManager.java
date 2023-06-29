package com.blankfactor.MaintainMe.web.resource;


import jakarta.validation.constraints.*;
import lombok.*;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class RegistrationRequestManager {

        @NotBlank
        @Size(min = 8,max = 50)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
        private String password;
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
