package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.web.resource.Login.LoginRequest;
import com.blankfactor.MaintainMe.web.resource.Login.LoginResponse;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller for user registration and log in authentication
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final LocalUserRepository repository;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginBody){
        String jwt=userService.loginUser(loginBody);
        if(jwt==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {

            User user  = repository.getUserByEmail(loginBody.getEmail());

            LoginResponse response=new LoginResponse();
            response.setJwt(jwt);
            response.setUser(user);
            //response.setRoleInBuilding(userService.getRoleInBuilding());
            return ResponseEntity.ok(response);

        }
    }

}
