package com.suraj.service.impl;

import com.suraj.model.Reel;
import com.suraj.model.User;
import com.suraj.repository.ReelRepository;
import com.suraj.service.ReelService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelServiceImpl implements ReelService {

	@Autowired
	private ReelRepository reelRepository;

	@Autowired
	private UserService userService;

	@Override
	public Reel createReel(Reel reel, User user) {
		Reel createReel = new Reel();

		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());

		return reelRepository.save(createReel);
	}

	@Override
	public List<Reel> findAllReels() {
		return reelRepository.findAll();
	}

	@Override
	public List<Reel> findUsersReels(Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		return reelRepository.findByUserId(user.getId());
	}
}
