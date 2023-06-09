package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.entity.Building;
import com.blankfactor.MaintainMe.entity.Notification;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.repository.UnitRepository;
import com.blankfactor.MaintainMe.web.resource.ProfileEditRequest;
import com.blankfactor.MaintainMe.web.resource.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {

    private final LocalUserRepository userRepository;
    private final UnitRepository unitRepository;

    private final EncryptionService encryptionService;


    public UserInfoResponse getInfo(){


        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        List units = unitRepository.findByUsers(authUser);

        UserInfoResponse response = new UserInfoResponse(authUser,units);

        return response;

    }

    @Transactional(rollbackFor = Exception.class)
    public User editInfo( ProfileEditRequest request){

        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        authUser.setEmail(request.getEmail());
        authUser.setPassword(encryptionService.encryptPassword(request.getPassword()));
        authUser.setFirstName(request.getFirstName());
        authUser.setLastName(request.getLastName());

        userRepository.save(authUser);
        return null;
    }

}