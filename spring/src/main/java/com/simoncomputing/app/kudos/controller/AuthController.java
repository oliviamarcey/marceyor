package com.simoncomputing.app.kudos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simoncomputing.app.kudos.entity.User;
import com.simoncomputing.app.kudos.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<User> success() {
		
	    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}