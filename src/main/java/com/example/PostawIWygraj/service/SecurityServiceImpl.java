package com.example.PostawIWygraj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;


    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {
	Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
	if (userDetails instanceof UserDetails) {
	    return ((UserDetails) userDetails).getUsername();
	}

	return null;
    }

    @Override
    public void autoLogin(String username, String password) {
	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
		userDetails, password, userDetails.getAuthorities());

	authenticationManager.authenticate(usernamePasswordAuthenticationToken);

	if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    logger.debug(String.format("Auto login %s successfully!", username));
	}
    }

    @Override
    public String getNameLoogedUser() {
	String username = "";
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
	    username = ((UserDetails) principal).getUsername();
	} else {
	    username = principal.toString();
	}
	return username;
    }

    @Override
    public Long getIdLoogerUser() {
	User user = userService.findByUsername(getNameLoogedUser());
	return user.getId();
    }

}
