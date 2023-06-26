package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.*;
import com.blankfactor.MaintainMe.repository.AddressRepository;
import com.blankfactor.MaintainMe.repository.BuildingRepository;
import com.blankfactor.MaintainMe.repository.UserRoleBuildingRepository;
import com.blankfactor.MaintainMe.web.assembler.BuildingAssembler;
import com.blankfactor.MaintainMe.web.exception.UserAlreadyExistsException;
import com.blankfactor.MaintainMe.web.resource.*;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.web.resource.Login.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final LocalUserRepository localUserRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final AddressRepository addressRepository;
    private final BuildingRepository buildingRepository;

    private final UserRoleBuildingRepository userRoleBuildingRepository;

    ManagerCreateUser managerCreateUser;


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
        BuildingAssembler buildingAssembler = new BuildingAssembler();
        Building building = buildingAssembler.fromResource(buildingResource);
        Building savedBuilding = buildingRepository.save(building);
        User user = registerUser(request.getRegistrationRequest());

        Role role = new Role();
        role.setId(2L);

        UserRoleBuilding userRoleBuilding = new UserRoleBuilding(user, role, savedBuilding);

        userRoleBuildingRepository.save(userRoleBuilding);


    }

    public Collection<Map<String, Object>> getBuildingsManagedByLoggedManager() {

        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Role role = new Role();
        role.setId(2L);
        Collection<Map<String, Object>> buildingId = userRoleBuildingRepository.getBuildingDataByUserIdAndRoleId(authUser.getId(), role.getId());
        return buildingId;

    }



        public String loginUser(LoginRequest loginBody) {
        Optional<User> optionalUser = localUserRepository.findByEmailIgnoreCase(loginBody.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                return jwtService.generateJWT(user);
            }

        }
        return null;
    }

    public void ManagerCreateUser(ManagerCreateUser managerCreateUser) throws UserAlreadyExistsException {

        UserRoleBuilding userRoleBuilding = new UserRoleBuilding();
        User user = registerUser(managerCreateUser.getRegistrationRequest());
        Building building = managerCreateUser.getBuilding();
        Role role = new Role();
        role.setId(1L);
        userRoleBuilding.setUser(user);
        userRoleBuilding.setBuilding(building);
        userRoleBuilding.setRole(role);

        userRoleBuildingRepository.save(userRoleBuilding);

        localUserRepository.save(user);
    }


}
