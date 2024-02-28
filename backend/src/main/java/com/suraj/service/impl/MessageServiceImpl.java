package com.suraj.service.impl;

import com.suraj.model.Chat;
import com.suraj.model.Message;
import com.suraj.model.User;
import com.suraj.repository.ChatRepository;
import com.suraj.repository.MessageRepository;
import com.suraj.service.ChatService;
import com.suraj.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private ChatService chatService;

	@Override
	public Message createMessage(User user, Integer chatId, Message messageReq) throws Exception {
		Chat chat = chatService.findChatById(chatId);

		Message message = new Message();

		message.setContent(messageReq.getContent());
		message.setImage(messageReq.getImage());
		message.setUser(user);
		message.setChat(chat);
		message.setTimestamp(LocalDateTime.now());

		Message savedMessage = messageRepository.save(message);

		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);

		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		Chat chat = chatService.findChatById(chatId);

		return messageRepository.findByChatId(chatId);
	}
}
