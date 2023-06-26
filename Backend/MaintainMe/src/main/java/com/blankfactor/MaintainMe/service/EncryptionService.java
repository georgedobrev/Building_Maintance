package com.blankfactor.MaintainMe.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    //used to encrypt the password of the user.

    @Value("${encryption.slat.rouns}")
    private int saltRounds;
    private String salt;

    @PostConstruct
    public void postConstruct(){
        salt= BCrypt.gensalt(saltRounds);
    }

    public String encryptPassword(String password){
        return BCrypt.hashpw(password,salt);

    }

    public boolean verifyPassword(String password,String hash){
        return BCrypt.checkpw(password,hash);
    }
}
