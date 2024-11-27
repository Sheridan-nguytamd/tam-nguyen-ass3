package ca.tam_nguyen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		return "User added with ID " + da.saveUser(user);
	}
	
	// PUT Method by ID
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public String updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		User existingUser = da.findUserById(id);
		if (existingUser != null) {
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setAge(user.getAge());
			existingUser.setGender(user.getGender());
			existingUser.setWeight(user.getWeight());
			existingUser.setHeight(user.getHeight());
			da.updateUser(existingUser);
			return "User updated";
		} else {
			return "User not found";
		}
	}
	
	// DELETE user by ID
	@DeleteMapping(value = "/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {
		User user = da.findUserById(id);
		if (user != null) {
			da.deleteUser(id);
			return "User deleted";
		}
		return "User not found";
	}
	
}
