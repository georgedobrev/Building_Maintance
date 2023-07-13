package com.blankfactor.MaintainMe.web.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.blankfactor.MaintainMe.entity.User;
import com.blankfactor.MaintainMe.repository.LocalUserRepository;
import com.blankfactor.MaintainMe.service.JWTService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class GoogleLoginFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private LocalUserRepository localUserRepository;


    public GoogleLoginFilter(JWTService jwtService, LocalUserRepository localUserRepository) {
        this.jwtService = jwtService;
        this.localUserRepository = localUserRepository;
    }

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token=servletRequest.getReader().readLine();

        if(token!=null ){
            try{
                String email =jwtService.getEmail(token);
                Optional<User> optionalUser= localUserRepository.findByEmailIgnoreCase(email);//we take the username and we try to find it
                if(optionalUser.isPresent()){
                    User user=optionalUser.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());//if the user is present we build the authentication
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(servletRequest));//we build some extra details so that the web authentication knows about the request
                    SecurityContextHolder.getContext().setAuthentication(authentication);// then the security context is set to have the authentication that we created
                }
            }
            catch (JWTDecodeException ex){
            }


        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
