package com.blankfactor.MaintainMe.web.resource;

import com.blankfactor.MaintainMe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String jwt;

    private User user;

}
