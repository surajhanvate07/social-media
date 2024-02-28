package com.suraj.service.impl;

import com.suraj.config.JwtProvider;
import com.suraj.exception.UserException;
import com.suraj.model.User;
import com.suraj.repository.UserRepository;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user1 = userRepository.findById(userId);
		if (user1.isPresent()) {
			return user1.get();
		}
		throw new UserException("User not exists with given user id " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User followUser(Integer currentUserId, Integer userId2) throws UserException {
		User currentUser = findUserById(currentUserId);

		User user2 = findUserById(userId2);

		user2.getFollowers().add(currentUser.getId());
		currentUser.getFollowing().add(user2.getId());

		userRepository.save(currentUser);
		userRepository.save(user2);

		return currentUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws UserException {
		Optional<User> oldUser = userRepository.findById(userId);
		if (oldUser.isEmpty()) {
			throw new UserException("User not exists with given user id " + userId);
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
			user1.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if(user.getGender() != null) {
			user1.setGender(user.getGender());
		}

		User updatedUser = userRepository.save(user1);
		return updatedUser;
	}


	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		return user;
	}
}
