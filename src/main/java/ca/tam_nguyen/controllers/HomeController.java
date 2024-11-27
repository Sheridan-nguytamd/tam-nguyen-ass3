package ca.tam_nguyen.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.tam_nguyen.beans.User;

@Controller
public class HomeController {

	final String REST_URL = "http://localhost:8080/api/users";
	
	// index.html
	@GetMapping("/")
	public String index(Model model, RestTemplate restTemplate) {
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity
				(REST_URL, User[].class);
		model.addAttribute("userList", responseEntity.getBody());
		return "index";
	}
	
	@GetMapping(value = "/getUser/{id}", produces = "application/json")
	@ResponseBody
	public User getUser(@PathVariable("id") Long id, RestTemplate restTemplate) {
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(REST_URL + "/" + id, User.class);
		return responseEntity.getBody();
	}
	
	// user.html
	@GetMapping("/user")
	public String addUser() {
		return "user";
	}
	
	// activities.html
	@GetMapping("/activities")
	public String userActivities(@RequestParam("userId") Long userId, Model model) {
	    model.addAttribute("userId", userId);
	    return "activities";
	}
	
	// activityForm.html
	@GetMapping("/activityForm")
	public String addActivity(@RequestParam("userId") Long userId, Model model) {
	    model.addAttribute("userId", userId);
	    return "activityForm";
	}
}
