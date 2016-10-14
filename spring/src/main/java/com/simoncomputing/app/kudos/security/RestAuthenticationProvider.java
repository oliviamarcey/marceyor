package com.simoncomputing.app.kudos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.simoncomputing.app.kudos.entity.User;
import com.simoncomputing.app.kudos.service.UserService;
  
@Component
public class RestAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    private UserService userService;
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	String name = authentication.getName();
        String password = (String) authentication.getCredentials();
    
        User user = userService.findOneByUsername(name);
          
        if (user == null ) {
        	throw new BadCredentialsException("User does not exist.");
        } else {
            if (!password.equals(user.getPassword())) {
                throw new BadCredentialsException("Wrong password.");
            } else {
                return new UsernamePasswordAuthenticationToken(user, password, null);
            }
        }
    }
 
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}