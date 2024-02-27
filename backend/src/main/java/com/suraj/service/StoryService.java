package com.suraj.service;

import com.suraj.model.Story;
import com.suraj.model.User;

import java.util.List;

public interface StoryService {

	public Story createStory(Story story, User user);

	public List<Story> getUserStoriesByUserId(Integer userId) throws Exception;
}
