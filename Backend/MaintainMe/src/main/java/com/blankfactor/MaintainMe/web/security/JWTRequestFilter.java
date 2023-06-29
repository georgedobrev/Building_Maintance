package com.blankfactor.MaintainMe.web.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private LocalUserRepository localUserRepository;

    public JWTRequestFilter(JWTService jwtService, LocalUserRepository localUserRepository) {
        this.jwtService = jwtService;
        this.localUserRepository = localUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader=request.getHeader("Authorization"); // we check of the request has authorization
        if(tokenHeader!=null && tokenHeader.startsWith("Bearer ")){ // does it start with Bearer
            String token= tokenHeader.substring(7); //then we brake it into a token since the token starts form the 7th character
            try{
                String email =jwtService.getEmail(token);//we try to decode it and get the username
                Optional<User> optionalUser= localUserRepository.findByEmailIgnoreCase(email);//we take the username and we try to find it
                if(optionalUser.isPresent()){
                    User user=optionalUser.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());//if the user is present we build the authentication
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//we build some extra details so that the web authentication knows about the request
                    SecurityContextHolder.getContext().setAuthentication(authentication);// then the security context is set to have the authentication that we created
                }
            }
            catch (JWTDecodeException ex){
            }


        }
        filterChain.doFilter(request,response);//call that to go and execute the next bit of data
    }
}
