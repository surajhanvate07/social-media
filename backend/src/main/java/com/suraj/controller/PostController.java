package com.suraj.controller;

import com.suraj.model.Post;
import com.suraj.model.User;
import com.suraj.response.APIResponse;
import com.suraj.service.PostService;
import com.suraj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	UserService userService;

	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		Post createdPost = postService.createNewPost(post, currentUser.getId());
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<APIResponse> deletePost(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		String message = postService.deletePost(postId, currentUser.getId());
		APIResponse response = new APIResponse(message, true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostById(@PathVariable("postId") Integer postId) throws Exception {
		return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
	}

	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> getUsersPost(@PathVariable("userId") Integer userId) {
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = postService.findAllPosts();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@PutMapping("/posts/saved/{postId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		Post post = postService.savedPost(postId, currentUser.getId());
		return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
	}

	@PutMapping("/posts/liked/{postId}")
	public ResponseEntity<Post> likedPostHandler(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		Post post = postService.likedPost(postId, currentUser.getId());
		return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
	}
}
