package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.service.UserService;
import com.blankfactor.MaintainMe.web.exception.UserAlreadyExistsException;
import com.blankfactor.MaintainMe.web.resource.ManagerRegistrationRequest;
import com.blankfactor.MaintainMe.web.resource.RegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Map;

public class UserController {

    private final UserService userService;
    private final LocalUserRepository repository;

    public UserController(UserService userService, LocalUserRepository repository) {
        this.userService = userService;
        this.repository = repository;
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

    @GetMapping("/role")
    public Map<String, Object> getRoleInBuilding() {
        return userService.getRoleInBuilding();
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){
        try {
            userService.registerUser(registrationRequest);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }


}
