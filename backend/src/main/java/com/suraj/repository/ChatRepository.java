package com.suraj.repository;

import com.suraj.model.Chat;
import com.suraj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	public List<Chat> findByUsersId(Integer userId);

	@Query("select c from Chat c where :otherUser Member of c.users And :currentUser Member of c.users")
	public Chat findChatByUsersId(@Param("otherUser") User otherUser, @Param("currentUser") User currentUser);
}
