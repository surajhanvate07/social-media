package com.suraj.controller;

import com.suraj.config.CustomUserDetailsServiceImpl;
import com.suraj.config.JwtProvider;
import com.suraj.model.User;
import com.suraj.repository.UserRepository;
import com.suraj.request.LoginRequest;
import com.suraj.response.AuthResponse;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsService;

	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {

		User isUserExists = userRepository.findByEmail(user.getEmail());

		if (isUserExists != null) {
			throw new Exception("User already Exists!, Please try with new email");
		}

		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse response = new AuthResponse(token, "Register Success");

		return response;
	}

	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse response = new AuthResponse(token, "SignIn Success");

		return response;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid UserName");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
