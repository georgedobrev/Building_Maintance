package com.blankfactor.MaintainMe.web.controller;

import com.blankfactor.MaintainMe.entity.Payment;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.service.PaymentService;
import com.blankfactor.MaintainMe.web.resource.PaymentRequest;
import lombok.AllArgsConstructor;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
@EnableScheduling
public class PaymentController {

    private final PaymentService paymentService;
    private final LocalUserRepository userRepository;


    @PostMapping("/make")
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequest request) throws Exception {
        return ResponseEntity.ok(paymentService.makePayment(request));
    }

    @GetMapping("/history")
    public List<Payment> paymentHistory() {
        return paymentService.getPaymentHistory();
    }

   // @Scheduled(cron = "0 0 12 1 * ?") // Run at 12 o'clock on the first day of every month

    @Scheduled(fixedRate = 1000, initialDelay = 5000)
    public void runTask() {
        // Simulate an authentication context
        User user = new User();
        user.setEmail("robi.prodanov@gmail.com");
        Authentication authentication = new CustomAuthentication(user);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Access the SecurityContextHolder within the scheduled method
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        System.out.println("email: " + authUser.getEmail());
        // Rest of your logic
    }

    private static class CustomAuthentication implements Authentication {
        private final User user;

        public CustomAuthentication(User user) {
            this.user = user;
        }

        // Implement the necessary methods of the Authentication interface
        // ...

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return user;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return null;
        }

        // Implement other necessary methods
        // ...
    }
}
//
//    private User authUser(){
//        User authUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        return authUser;
//    }



