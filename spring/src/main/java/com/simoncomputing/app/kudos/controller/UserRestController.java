package com.simoncomputing.app.kudos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simoncomputing.app.kudos.entity.User;
import com.simoncomputing.app.kudos.service.UserService;


@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> users = userService.findAll();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {

		User user = userService.findOne(userId);

		return user == null ? new ResponseEntity<User>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		User updatedUser = userService.save(user);

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable Long userId) {

		User u = userService.findOne(userId);
		
		if (u == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			userService.delete(userId);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}
}