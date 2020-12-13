package com.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.model.Post;
import com.tutorial.model.User;
import com.tutorial.service.UserService;

@RestController
@RequestMapping("/jpa")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping(value = "/users/{userId}")
	public ResponseEntity<User> getUsers(@PathVariable long userId) throws Exception {
		try {
			return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}
	@GetMapping(value = "/users/{userId}/posts")
	public ResponseEntity<List<Post>> getPostsForUser(@PathVariable long userId) throws Exception {
		try {
			return new ResponseEntity<>(userService.getPostsForUser(userId), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}
	@GetMapping(value = "/users/{userId}/posts/{postId}")
	public ResponseEntity<Post> getPostForUser(@PathVariable long userId,@PathVariable long postId) throws Exception {
		try {
			return new ResponseEntity<>(userService.getPostForUser(userId,postId), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
