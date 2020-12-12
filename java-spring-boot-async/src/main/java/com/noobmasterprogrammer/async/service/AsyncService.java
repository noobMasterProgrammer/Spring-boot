package com.noobmasterprogrammer.async.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncService {

	@Autowired
	private RestTemplate restTemplate;
	@Async("threadPoolTaskExecutor")
    public CompletableFuture<String> findFutureResult(String url) throws InterruptedException {        
        String results = restTemplate.getForObject(url, String.class);        
        return CompletableFuture.completedFuture(results);
    }
}
