package com.blankfactor.MaintainMe.web.utilities;

public class GmailChecker {



    public static boolean isGoogleEmail(String email) {
        String googleDomain = "gmail.com";
        int atIndex = email.lastIndexOf("@");

        if (atIndex != -1) {
            String domain = email.substring(atIndex + 1).toLowerCase();
            return domain.equals(googleDomain);
        }

        return false;
    }

}
