package com.simoncomputing.app.kudos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simoncomputing.app.kudos.entity.Activity;
import com.simoncomputing.app.kudos.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity save(Activity be) {
		return activityRepository.save(be);
	}

	@Override
	public Activity findOne(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		activityRepository.delete(id);
	}
	
	@Override
	public List<Activity> findAllByKudosId(Long kudosId) {
		return activityRepository.findAllByKudosId(kudosId);
	}

}
