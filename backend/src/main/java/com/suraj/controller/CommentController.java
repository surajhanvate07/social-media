package com.suraj.controller;

import com.suraj.model.Comment;
import com.suraj.model.User;
import com.suraj.service.CommentService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@PostMapping("/api/comments/posts/{postId}")
	public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt, @PathVariable("postId") Integer postId) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);

		Comment newComment = commentService.createComment(comment, postId, currentUser.getId());

		return newComment;
	}

	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable("commentId") Integer commentId) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);

		Comment likedComment = commentService.likeComment(commentId, currentUser.getId());

		return likedComment;
	}
}
