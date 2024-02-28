package com.suraj.service;

import com.suraj.model.Chat;
import com.suraj.model.Message;
import com.suraj.model.User;

import java.util.List;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message messageReq) throws Exception;

	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
