package com.suraj.controller;

import com.suraj.model.Story;
import com.suraj.model.User;
import com.suraj.service.StoryService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

	@Autowired
	private StoryService storyService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
		User currentUser = userService.findUserByJwt(jwt);

		Story createdStory = storyService.createStory(story, currentUser);

		return createdStory;
	}

	@GetMapping("/api/story/user/{userId}")
	public List<Story> getUserStories(@PathVariable("userId") Integer userId) throws Exception {
		return storyService.getUserStoriesByUserId(userId);
	}
}
