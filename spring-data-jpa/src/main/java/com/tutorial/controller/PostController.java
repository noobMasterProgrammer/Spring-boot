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
import com.tutorial.service.PostService;

@RestController
@RequestMapping("/jpa")
public class PostController {
	@Autowired
	PostService postService;

	@GetMapping(value = "/posts")
	public ResponseEntity<List<Post>> getUsers() {
		return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
	}

	@GetMapping(value = "/posts/{postId}")
	public ResponseEntity<Post> getUsers(@PathVariable long postId) throws Exception {
		try {
			return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
