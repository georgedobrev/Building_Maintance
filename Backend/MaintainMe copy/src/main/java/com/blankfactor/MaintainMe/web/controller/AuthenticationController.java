package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.entity.UserRoleBuilding;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.web.exception.UserAlreadyExistsException;
import com.blankfactor.MaintainMe.web.resource.LoginRequest;
import com.blankfactor.MaintainMe.web.resource.LoginResponse;
import com.blankfactor.MaintainMe.web.resource.ManagerRegistrationRequest;
import com.blankfactor.MaintainMe.web.resource.RegistrationRequest;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//Controller for user registration and log in authentication
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final LocalUserRepository repository;



    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        try {
            userService.registerUser(registrationRequest);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

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
            return ResponseEntity.ok(response);

        }
    }

    @PostMapping("/register/manager")
    public ResponseEntity registerManager(@Valid @RequestBody ManagerRegistrationRequest managerRegistrationRequest){
        try {
            userService.registerManager(managerRegistrationRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user){ //when spring calls this method it will
        // automatically go into the get Authentication it will cast it onto the user and inject it as a parameter

        return user;


    }

    @GetMapping("/managed/buildings")
    public Collection<Map<String, Object>> getBuildings(){
       return userService.getBuildingsManagedByLoggedManager();
    }

    //@GetMapping("/managed/buildings/{id}")

}
