package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.*;
import com.blankfactor.MaintainMe.repository.AddressRepository;
import com.blankfactor.MaintainMe.repository.BuildingRepository;
import com.blankfactor.MaintainMe.repository.UserRoleBuildingRepository;
import com.blankfactor.MaintainMe.web.assembler.BuildingAssembler;
import com.blankfactor.MaintainMe.web.exception.UserAlreadyExistsException;
import com.blankfactor.MaintainMe.web.resource.*;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private LocalUserRepository localUserRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    private AddressRepository addressRepository;
    private BuildingRepository buildingRepository;

    private UserRoleBuildingRepository userRoleBuildingRepository;


    public UserService(LocalUserRepository localUserRepository, EncryptionService encryptionService, JWTService jwtService, AddressRepository addressRepository, BuildingRepository buildingRepository, UserRoleBuildingRepository userRoleBuildingRepository) {
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.addressRepository = addressRepository;
        this.buildingRepository = buildingRepository;
        this.userRoleBuildingRepository = userRoleBuildingRepository;
    }

    public User registerUser(RegistrationRequest registrationRequest) throws UserAlreadyExistsException {
        if (localUserRepository.findByEmailIgnoreCase(registrationRequest.getEmail()).isPresent() ||
                localUserRepository.findByUsernameIgnoreCase(registrationRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationRequest.getPassword()));
        return localUserRepository.save(user);


    }

    @Transactional(rollbackFor = Exception.class)
    public void registerManager(ManagerRegistrationRequest request) throws UserAlreadyExistsException {


        BuildingResource buildingResource = request.getBuildingResource();
        BuildingAssembler buildingAssembler=new BuildingAssembler();
        Building building = buildingAssembler.fromResource(buildingResource);
        Building savedBuilding = buildingRepository.save(building);
        User user = registerUser(request.getRegistrationRequest());

        Role role= new Role();
        role.setId(2L);

        UserRoleBuilding userRoleBuilding=new UserRoleBuilding(user,role,savedBuilding);

        userRoleBuildingRepository.save(userRoleBuilding);


    }









    public String loginUser(LoginRequest loginBody) {
        Optional<User> optionalUser = localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                // Create authentication token with the user's details
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

                // Set the created authentication in the security context
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

                // Return the authenticated user or generate JWT here
                return jwtService.generateJWT(user);
            }
        }
        // Throw an exception or return an error response for invalid credentials
        throw new RuntimeException("Invalid credentials");
    }


}
