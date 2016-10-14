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

import com.simoncomputing.app.kudos.entity.Activity;
import com.simoncomputing.app.kudos.service.ActivityService;


@RestController
@RequestMapping("/activity")
public class ActivityRestController {
	
	@Autowired
	private ActivityService activityService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Activity>> getAllActivitys() {

		List<Activity> activitys = activityService.findAll();
		
		return new ResponseEntity<List<Activity>>(activitys, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{activityId}")
	public ResponseEntity<Activity> getActivity(@PathVariable Long activityId) {

		Activity activity = activityService.findOne(activityId);

		return activity == null ? new ResponseEntity<Activity>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<Activity>(activity, HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<Activity> saveActivity(@RequestBody Activity activity) {

		Activity updatedActivity = activityService.save(activity);

		return new ResponseEntity<Activity>(updatedActivity, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{activityId}")
	public ResponseEntity<Activity> deleteActivity(@PathVariable Long activityId) {

		Activity u = activityService.findOne(activityId);
		
		if (u == null) {
			return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
		} else {
			activityService.delete(activityId);
			return new ResponseEntity<Activity>(u, HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "kudos/{kudosId}")
	public ResponseEntity<List<Activity>> getActivitiesByKudoId (@PathVariable Long kudosId)  {

		List<Activity> activities = activityService.findAllByKudosId(kudosId);
		
		return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);

	}
}