package com.blankfactor.MaintainMe.web.utilities;
import org.apache.commons.lang3.RandomStringUtils;
public class RandomPassword {


    public static String generateRandomPassword() {
        int passwordLength = 8; // Set the desired length of the password
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedPassword = RandomStringUtils.random(passwordLength, useLetters, useNumbers);
        return generatedPassword;
    }

}
