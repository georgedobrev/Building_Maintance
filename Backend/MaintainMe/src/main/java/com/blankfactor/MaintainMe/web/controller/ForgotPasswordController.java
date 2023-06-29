package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.PasswordResetToken;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.ResetTokenRepository;
import com.blankfactor.MaintainMe.service.EmailService;
import com.blankfactor.MaintainMe.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@AllArgsConstructor
public class ForgotPasswordController {

    // TODO: 27.6.2023 г. fix return types 
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final EmailService emailService;

    private ResetTokenRepository tokenRepository;

    @PostMapping("/forgot_password")
        public String processForgotPassword(HttpServletRequest request, Model model) throws IOException {

        BufferedReader reader = request.getReader();
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObject = jsonReader.readObject();

        String email = jsonObject.getString("email");
        String token = RandomStringUtils.randomAlphabetic(10);


            try {
                userService.updateResetPasswordToken(token, email);
                String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
                emailService.sendEmailForgottenPassword(email, resetPasswordLink);
                model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

            } catch (UnsupportedEncodingException | MessagingException e) {
                model.addAttribute("error", "Error while sending email");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        return "forgot_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) throws IOException {

        BufferedReader reader = request.getReader();
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObject = jsonReader.readObject();

        String token = jsonObject.getString("token");
        String password = jsonObject.getString("password");

        System.out.println("token " + token);
        System.out.println("password " + password);

        User user = userService.getByResetPasswordToken(token);

        PasswordResetToken checkToken = tokenRepository.getPasswordResetTokenByEmail(user.getEmail());

        model.addAttribute("title", "Reset your password");

        Date date = new Date();

        if (user == null || checkToken.getExpiryDate().before(date)) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        }

        System.out.println(model);
        return "message";
    }
}
