package com.suraj.service.impl;

import com.suraj.model.User;
import com.suraj.repository.UserRepository;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());

		return userRepository.save(newUser);
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user1 = userRepository.findById(userId);
		if (user1.isPresent()) {
			return user1.get();
		}
		throw new Exception("User not exists with given user id " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		User user1 = findUserById(userId1);

		User user2 = findUserById(userId2);

		user2.getFollowers().add(user1.getId());
		user1.getFollowing().add(user2.getId());

		userRepository.save(user1);
		userRepository.save(user2);

		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> oldUser = userRepository.findById(userId);
		if (oldUser.isEmpty()) {
			throw new Exception("User not exists with given user id " + userId);
		}

		User user1 = oldUser.get();
		if (user.getFirstName() != null) {
			user1.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			user1.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			user1.setEmail(user.getEmail());
		}
		if (user.getPassword() != null) {
			user1.setPassword(user.getPassword());
		}

		User updatedUser = userRepository.save(user1);
		return updatedUser;
	}


	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}
}
