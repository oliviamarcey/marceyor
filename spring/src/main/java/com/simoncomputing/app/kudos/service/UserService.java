package com.simoncomputing.app.kudos.service;

import java.util.List;

import com.simoncomputing.app.kudos.entity.User;

public interface UserService {
	public User save(User be);

	public User findOne(Long id);

	public List<User> findAll();

	public void delete(Long id);
	
	public User findOneByUsername(String username);
}
