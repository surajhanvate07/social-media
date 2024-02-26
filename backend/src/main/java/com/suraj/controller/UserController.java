package com.suraj.controller;

import com.suraj.model.User;
import com.suraj.repository.UserRepository;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	@PostMapping("/users/create-user")
	public User createUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> userList = userRepository.findAll();

		return userList;
	}

	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer userId) throws Exception {
		return userService.findUserById(userId);
	}

//	@GetMapping("/users/{email}")
//	public User getUserByEmail(@PathVariable("email") String email) {
//		return userService.findUserByEmail(email);
//	}

	@PutMapping("/api/users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable("userId") Integer userId) throws Exception {
		return userService.updateUser(user, userId);
	}

	@PutMapping("/api/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
		return userService.followUser(userId1, userId2);
	}

	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		return userService.searchUser(query);
	}

//	public String deleteUser()
}
