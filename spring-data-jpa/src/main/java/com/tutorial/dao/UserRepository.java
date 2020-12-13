package com.tutorial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
