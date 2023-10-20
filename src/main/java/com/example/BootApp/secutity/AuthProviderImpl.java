package com.example.BootApp.secutity;

import com.example.BootApp.services.PeopleService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PeopleService peopleService;

    public AuthProviderImpl(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String userName=authentication.getName();

     UserDetails personDetails = peopleService.loadUserByUsername(userName);
     String password=authentication.getCredentials().toString();

     if(!password.equals(personDetails.getPassword())) {
        throw new BadCredentialsException("Incorrect password");
      }

     return new UsernamePasswordAuthenticationToken(personDetails,password, Collections.emptyList());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
