package com.suraj.controller;

import com.suraj.exception.UserException;
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

	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> userList = userRepository.findAll();

		return userList;
	}

	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer userId) throws UserException {
		return userService.findUserById(userId);
	}

//	@GetMapping("/users/{email}")
//	public User getUserByEmail(@PathVariable("email") String email) {
//		return userService.findUserByEmail(email);
//	}

	@PutMapping("/api/users")
	public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws UserException {
		User currentUser = userService.findUserByJwt(jwt);
		return userService.updateUser(user, currentUser.getId());
	}

	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
		User currentUser = userService.findUserByJwt(jwt);
		return userService.followUser(currentUser.getId(), userId2);
	}

	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		return userService.searchUser(query);
	}

//	public String deleteUser()

	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
}
