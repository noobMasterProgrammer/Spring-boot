package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Pets;

@RestController
@RequestMapping("/pets")
public class PetsController {
	@Autowired 
	MongoTemplate mongoTemplate;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Pets> getAllPets() {
		return mongoTemplate.findAll(Pets.class, "User");
	}

}