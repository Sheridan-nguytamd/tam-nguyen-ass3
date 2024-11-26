package ca.tam_nguyen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.tam_nguyen.beans.User;
import ca.tam_nguyen.database.DatabaseAccess;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private DatabaseAccess da;
	
	// GET Collection
	
	@GetMapping
	public List<User> getUser() {
		return da.findAllUsers();
	}
	
	// GET user by ID
	
	@GetMapping(value = "/{id}")
	public User getEachUser(@PathVariable("id") Long id) {
		return da.findUserById(id);
	}
	
	// POST Method
	
	@PostMapping(consumes = "application/json")
	public String postUser(@RequestBody User user) {
		return "http://localhost:8080/api/users/" + da.saveUser(user);
	}
	
	// PUT Method by ID
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public String updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		User existingUser = da.findUserById(id);
		if (existingUser != null) {
			existingUser.setName(user.getName());
			da.updateUser(existingUser);
			return "User updated";
		} else {
			return "Student not found";
		}
	}
	
	// DELETE 
}
