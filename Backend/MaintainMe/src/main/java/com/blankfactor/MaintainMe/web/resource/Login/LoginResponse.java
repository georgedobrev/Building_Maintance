package com.blankfactor.MaintainMe.web.resource.Login;

import com.blankfactor.MaintainMe.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String jwt;

    private User user;

    private Map<String, Object> roleInBuilding;

}
