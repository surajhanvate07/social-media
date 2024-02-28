package com.suraj.service.impl;

import com.suraj.model.Chat;
import com.suraj.model.User;
import com.suraj.repository.ChatRepository;
import com.suraj.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(User currentUser, User otheUser) {
		Chat isExists = chatRepository.findChatByUsersId(otheUser, currentUser);

		if (isExists != null) {
			return isExists;
		}

		Chat chat = new Chat();
		chat.getUsers().add(otheUser);
		chat.getUsers().add(currentUser);
		chat.setTimestamp(LocalDateTime.now());

		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> chat = chatRepository.findById(chatId);

		if (chat.isEmpty()) {
			throw new Exception("Chat not found with Id: " + chat);
		}

		return chat.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		return chatRepository.findByUsersId(userId);
	}
}
