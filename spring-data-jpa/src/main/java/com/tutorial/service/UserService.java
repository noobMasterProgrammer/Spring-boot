package com.tutorial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tutorial.dao.PostRepository;
import com.tutorial.dao.UserRepository;
import com.tutorial.exception.CustomException;
import com.tutorial.model.Post;
import com.tutorial.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User findUserById(long userId) throws CustomException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new CustomException("User not found for userid : " + userId, HttpStatus.BAD_REQUEST);
	}

	public List<Post> getPostsForUser(long userId) throws CustomException {
		User user = findUserById(userId);
		List<Post> optionalPosts = postRepository.getPostsForUser(user.getUserId());
		if (CollectionUtils.isEmpty(optionalPosts)) {
			throw new CustomException("Posts not found for userid : " + userId, HttpStatus.NO_CONTENT);
		}
		return optionalPosts;
	}

	public Post getPostForUser(long userId, long postId) throws CustomException {
		User user = findUserById(userId);
		Post post = postRepository.getPostForUser(user.getUserId(), postId);
		if (null == post) {
			throw new CustomException("Posts not found for userid : " + userId, HttpStatus.NO_CONTENT);
		}
		return post;
	}
}
