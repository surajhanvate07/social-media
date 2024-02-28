package com.suraj.service;

import com.suraj.model.Chat;
import com.suraj.model.User;

import java.util.List;

public interface ChatService {

	public Chat createChat(User currentUser, User otheUser);

	public Chat findChatById(Integer chatId) throws Exception;

	public List<Chat> findUsersChat(Integer userId);
}
