package com.suraj.service.impl;

import com.suraj.model.Comment;
import com.suraj.model.Post;
import com.suraj.model.User;
import com.suraj.repository.CommentRepository;
import com.suraj.repository.PostRepository;
import com.suraj.service.CommentService;
import com.suraj.service.PostService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		User currentUser = userService.findUserById(userId);

		Post post = postService.findPostById(postId);

		Comment newComment = new Comment();

		newComment.setUser(currentUser);
		newComment.setContent(comment.getContent());
		newComment.setCreatedAt(LocalDateTime.now());

		Comment savedComment = commentRepository.save(newComment);

		post.getComments().add(savedComment);

		postRepository.save(post);

		return savedComment;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		Comment comment = findCommentById(commentId);
		User user = userService.findUserById(userId);

		if (!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		} else {
			comment.getLiked().remove(user);
		}

		return commentRepository.save(comment);
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Optional<Comment> comment = commentRepository.findById(commentId);

		if (comment.isEmpty()) {
			throw new Exception("Comment doesn't exists!");
		}
		return comment.get();
	}
}
