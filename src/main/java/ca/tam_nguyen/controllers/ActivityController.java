package ca.tam_nguyen.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.tam_nguyen.beans.Activity;
import ca.tam_nguyen.database.DatabaseAccess;

@RestController
@RequestMapping("/api")
public class ActivityController {

	@Autowired
	private DatabaseAccess da;
	
	// POST users/id/activity
	@PostMapping(value = "/users/{id}/activity", consumes = "application/json")
	public Activity logActivity(@RequestBody Activity activity, @PathVariable Long id) {
		activity.setUserId(id);
		activity.setActivityDate(LocalDateTime.now());
		Long activityId = da.saveActivity(activity);
		activity.setId(activityId);
		return activity;
	}
	
	// GET users/id/activities
	@GetMapping(value = "/users/{id}/activities")
	public List<Activity> getUserActivities(@PathVariable Long id) {
		return da.findActivityByUserId(id);
	}
	
	// GET activities Id
	@GetMapping(value = "/activities/{id}")
	public Activity getActivityId(@PathVariable("id") Long id) {
		return da.findActivityById(id);
	}
	
	// DELETE activities by Id
	@DeleteMapping(value = "/activities/{id}")
	public String deleteActivitiesbyId(@PathVariable("id") Long id) {
		Activity activity = da.findActivityById(id);
		if (activity != null) {
			da.deleteActivity(id);
			return "Activity deleted";
		}
		return "Activity not found";
	}
	
	// DELETE all activities
	@DeleteMapping(value = "/activities")
	public String deleteAllActivities() {
		da.deleteAllActivity();
		return "All activities deleted";
	}
}

