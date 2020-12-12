package com.noobmasterprogrammer.async.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.noobmasterprogrammer.async.service.AsyncService;

@RestController
public class FutureController {
	
	@Autowired
	private RestTemplate restTemplate;
	private String url="http://gturnquist-quoters.cfapps.io/api";
	@Autowired
	private AsyncService service;
	
	@GetMapping("/withoutFuture")
	public Map<String,String> withoutFuture() throws InterruptedException{
		long start= System.currentTimeMillis();
		
		for(int i=0;i<100;i++) {
			restTemplate.getForObject(url, String.class);
		}
		long end= System.currentTimeMillis();
		Map<String,String> mp= new HashMap<String,String>();
		mp.put("message","success");
		int totalTime=(int)(end-start)/1000;
		mp.put("time",totalTime+"");
		return mp;
	}

	@GetMapping("/withFuture")
	public Map<String,String> withFuture() throws InterruptedException{
		ExecutorService service=Executors.newFixedThreadPool(20);
		List<Future<String>> list = new ArrayList<Future<String>>();
		long start= System.currentTimeMillis();
		
		for(int i=0;i<100;i++) {
			Future<String> future= service.submit(()->restTemplate.getForObject(url, String.class));
			list.add(future);
		}
		list.stream().forEach((future)->{
			try {
				future.get();
				//System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		long end= System.currentTimeMillis();
		Map<String,String> mp= new HashMap<String,String>();
		mp.put("message","success");
		long totalTime=(int)(end-start)/1000;
		mp.put("time",totalTime+"");
		return mp;
	}
	@GetMapping("/withCompletableFuture")
	public Map<String,String> withCompletableFuture() throws InterruptedException{
		ExecutorService service=Executors.newFixedThreadPool(20);
		List<CompletableFuture<String>> list = new ArrayList<CompletableFuture<String>>();
		long start= System.currentTimeMillis();
		
		for(int i=0;i<100;i++) {
			CompletableFuture<String> future= CompletableFuture
					.supplyAsync(()->restTemplate.getForObject(url, String.class), service);
			list.add(future);
		}
		list.stream().forEach((future)->{
			try {
				future.get();
				//System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		long end= System.currentTimeMillis();
		Map<String,String> mp= new HashMap<String,String>();
		mp.put("message","success");
		long totalTime=(int)(end-start)/1000;
		mp.put("time",totalTime+"");
		return mp;
	}
	
	@GetMapping("/withAsyncAnnotation")
	public Map<String,String> withAsyncAnnotation() throws InterruptedException{
		List<CompletableFuture<String>> list = new ArrayList<CompletableFuture<String>>();
		long start= System.currentTimeMillis();
		
		for(int i=0;i<100;i++) {
			CompletableFuture<String> future = service.findFutureResult(url);
			list.add(future);
		}
		list.stream().forEach((future)->{
			try {
				//future.get();
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		long end= System.currentTimeMillis();
		Map<String,String> mp= new HashMap<String,String>();
		mp.put("message","success");
		long totalTime=(int)(end-start)/1000;
		mp.put("time",totalTime+"");
		return mp;
	}
}
