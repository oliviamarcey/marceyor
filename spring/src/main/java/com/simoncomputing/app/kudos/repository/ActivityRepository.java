package com.simoncomputing.app.kudos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simoncomputing.app.kudos.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> { 
	public List<Activity> findAllByKudosId(Long kudosId);
}
