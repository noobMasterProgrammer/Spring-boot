package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.library.Greeter;

@SpringBootApplication
public class CustomStarterApplication implements CommandLineRunner{

	@Autowired
	private Greeter greeter;
	public static void main(String[] args) {
		SpringApplication.run(CustomStarterApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		greeter.setName("Abhishek Ghosh");
		greeter.setPassword("very complex password");
		greeter.setDesignation("Manager");
		greeter.greet();
	}

}
