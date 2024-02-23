package com.suraj.service;


import com.suraj.model.Post;

import java.util.List;

public interface PostService {

	Post createNewPost(Post post, Integer userId) throws Exception;

	String deletePost(Integer postId, Integer userId) throws Exception;

	Post findPostById(Integer postId) throws Exception;

	List<Post> findAllPosts();

	List<Post> findPostByUserId(Integer userId);

	Post savedPost(Integer postId, Integer userId) throws Exception;

	Post likedPost(Integer postId, Integer userId) throws Exception;

}
