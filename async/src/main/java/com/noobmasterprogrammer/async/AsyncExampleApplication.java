package com.noobmasterprogrammer.async;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.noobmasterprogrammer.async.model.Credential;
import com.noobmasterprogrammer.async.model.Description;
import com.noobmasterprogrammer.async.model.User;

@SpringBootApplication
@EnableAsync
public class AsyncExampleApplication implements CommandLineRunner {

	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AsyncExampleApplication.class, args);
	}

	@Bean()
	public RestTemplate getBean() {
		return new RestTemplate();
	}

	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(500);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-");
		return executor;
	}

	@Override
	public void run(String... args) throws Exception {

		CompletableFuture<Credential> credentialFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Credential is fetching");
			return restTemplate.getForObject("http://192.168.0.105:5001/getCredential", Credential.class);
		}).exceptionally((ex) -> {
			System.out.println(ex.getMessage());
			return new Credential();
		});

		System.out.println("line after Credential is fetching");

		CompletableFuture<Description> descriptionFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("Description is fetching");
			return restTemplate.getForObject("http://192.168.0.105:5001/getDescription", Description.class);
		}).exceptionally((ex) -> {
			System.out.println(ex.getMessage());
			return new Description();
		});
		;

		System.out.println("line after Description is fetching");
		CompletableFuture<User> combinedFuture = credentialFuture
				.thenCombine(descriptionFuture, (credential, description) -> {
					if (credential.getId() == 0 || description.getId() == 0) {
						throw new RuntimeException("There was a problem while fetching data from the server");
					}
					return new User(credential, description);
				}).handle((res, ex) -> {
					if (ex != null) {
						System.out.println(ex.getMessage());
						return new User();
					}
					return res;
				});

		System.out.println(combinedFuture.get());

	}
}
