package com.suraj.controller;

import com.suraj.model.Message;
import com.suraj.model.User;
import com.suraj.service.MessageService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestBody Message messageReq, @RequestHeader("Authorization") String jwt, @PathVariable("chatId") Integer chatId) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);

		Message message = messageService.createMessage(currentUser, chatId, messageReq);

		return message;
	}

	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatsMessages(@RequestHeader("Authorization") String jwt, @PathVariable("chatId") Integer chatId) throws Exception {

		User user = userService.findUserByJwt(jwt);

		List<Message> messages = messageService.findChatsMessages(chatId);

		return messages;
	}
}
