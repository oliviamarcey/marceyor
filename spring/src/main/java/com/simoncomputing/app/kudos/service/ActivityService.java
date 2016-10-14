package com.simoncomputing.app.kudos.service;

import java.util.List;

import com.simoncomputing.app.kudos.entity.Activity;

public interface ActivityService {
	public Activity save(Activity be);

	public Activity findOne(Long id);

	public List<Activity> findAll();

	public void delete(Long id);
	
	public List<Activity> findAllByKudosId(Long kudosId);
}
