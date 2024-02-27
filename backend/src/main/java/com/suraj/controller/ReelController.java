package com.suraj.controller;

import com.suraj.model.Reel;
import com.suraj.model.User;
import com.suraj.service.ReelService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelController {

	@Autowired
	private ReelService reelService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/reel")
	public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt) {
		User currentUser = userService.findUserByJwt(jwt);
		Reel createdReel = reelService.createReel(reel, currentUser);

		return createdReel;
	}

	@GetMapping("/api/reels")
	public List<Reel> getAllReels() {
		return reelService.findAllReels();
	}

	@GetMapping("/api/reels/user/{userId}")
	public List<Reel> getUsersReels(@PathVariable Integer userId) throws Exception {
		return reelService.findUsersReels(userId);
	}
}
