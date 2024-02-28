package com.suraj.controller;

import com.suraj.model.Chat;
import com.suraj.model.User;
import com.suraj.request.CreateChatRequest;
import com.suraj.service.ChatService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest chatRequest) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);

		User otherUser = userService.findUserById(chatRequest.getOtherUserId());

		Chat chat = chatService.createChat(currentUser, otherUser);

		return chat;
	}

	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
		User currentUser = userService.findUserByJwt(jwt);

		List<Chat> chats = chatService.findUsersChat(currentUser.getId());

		return chats;
	}
}
