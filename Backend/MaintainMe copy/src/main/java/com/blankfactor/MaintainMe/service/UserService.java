package com.blankfactor.MaintainMe.service;

import com.blankfactor.MaintainMe.web.exception.UserAlreadyExistsException;
import com.blankfactor.MaintainMe.web.resource.LoginRequest;
import com.blankfactor.MaintainMe.web.resource.RegistrationRequest;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.entity.repository.LocalUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private LocalUserRepository localUserRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(LocalUserRepository localUserRepository, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
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

public String loginUser(LoginRequest loginBody){
    Optional<User> optionalUser= localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
    if(optionalUser.isPresent()){
        User user=optionalUser.get();
        if(encryptionService.verifyPassword(loginBody.getPassword(),user.getPassword())){
            return jwtService.generateJWT(user);
        }

    }
    return null;
}

}
