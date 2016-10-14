package com.simoncomputing.app.kudos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simoncomputing.app.kudos.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findOneByUsername(String username);
	
}
