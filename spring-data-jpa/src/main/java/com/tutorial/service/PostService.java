package com.tutorial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tutorial.dao.PostRepository;
import com.tutorial.exception.CustomException;
import com.tutorial.model.Post;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	
	public List<Post> getPosts(){
		return postRepository.findAll();
	}
	public Post findPostById(long postId) throws CustomException {
		Optional<Post> optionalPost =postRepository.findById(postId);
		if(optionalPost.isPresent()) {
			return optionalPost.get();
		}
		throw new CustomException("Post not found for postId : "+postId,HttpStatus.BAD_REQUEST);
	}
}
