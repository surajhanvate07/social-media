package com.suraj.controller;

import com.suraj.model.Post;
import com.suraj.response.APIResponse;
import com.suraj.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable("userId") Integer userId) throws Exception {
		Post createdPost = postService.createNewPost(post, userId);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<APIResponse> deletePost(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {
		String message = postService.deletePost(postId, userId);
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

	@PutMapping("/posts/saved/{postId}/user/{userId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {
		Post post = postService.savedPost(postId, userId);
		return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
	}

	@PutMapping("/posts/liked/{postId}/user/{userId}")
	public ResponseEntity<Post> likedPostHandler(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) throws Exception {
		Post post = postService.likedPost(postId, userId);
		return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
	}
}
