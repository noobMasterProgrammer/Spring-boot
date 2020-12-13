package com.tutorial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tutorial.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(value = "SELECT * FROM POSTS p WHERE p.USER_ID = ?1", nativeQuery = true)
	List<Post> getPostsForUser(Long userId);
	
	@Query(value = "SELECT * FROM POSTS p WHERE p.USER_ID = ?1 and p.POST_ID = ?2", nativeQuery = true)
	Post getPostForUser(Long userId,Long postId);
}
