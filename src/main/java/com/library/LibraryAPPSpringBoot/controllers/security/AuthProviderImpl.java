package com.library.LibraryAPPSpringBoot.controllers.security;

import com.library.LibraryAPPSpringBoot.controllers.services.AccountService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider{

    private final AccountService accountService;

    public AuthProviderImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails accountDetails = accountService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();

        if(!password.equals(accountDetails.getPassword()))
            throw new BadCredentialsException("Incorrect Password");

        return new UsernamePasswordAuthenticationToken(accountDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
