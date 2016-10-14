package com.simoncomputing.app.kudos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simoncomputing.app.kudos.entity.User;
import com.simoncomputing.app.kudos.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User be) {
		return userRepository.save(be);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}
	
	@Override
	public User findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}
}
