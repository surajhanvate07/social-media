package com.suraj.service.impl;

import com.suraj.model.Story;
import com.suraj.model.User;
import com.suraj.repository.StoryRepository;
import com.suraj.service.StoryService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	private StoryRepository storyRepository;

	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, User user) {
		Story newStory = new Story();

		newStory.setCaption(story.getCaption());
		newStory.setImage(story.getImage());
		newStory.setUser(user);
		newStory.setTimestamp(LocalDateTime.now());

		return storyRepository.save(newStory);
	}

	@Override
	public List<Story> getUserStoriesByUserId(Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		return storyRepository.findByUserId(user.getId());
	}

}
