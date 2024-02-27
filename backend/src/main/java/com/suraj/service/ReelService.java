package com.suraj.service;

import com.suraj.model.Reel;
import com.suraj.model.User;

import java.util.List;

public interface ReelService {

	public Reel createReel(Reel reel, User user);

	public List<Reel> findAllReels();

	public List<Reel> findUsersReels(Integer userId) throws Exception;
}
