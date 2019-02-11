package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.EmployeeNames;
import com.example.demo.model.EmployeePhone;

@Service
public class AsyncService {
	
	private static Logger logger = LoggerFactory.getLogger(AsyncService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Async("asyncExecutor")
	public CompletableFuture<EmployeeAddress> getEmployeeAddresses() throws InterruptedException{
		logger.info("enter getEmployeeAddresses()");
		EmployeeAddress addresses = restTemplate.getForObject("http://localhost:8080/getAddress", EmployeeAddress.class);
		logger.info("EmployeeAddress {} ", addresses);
		Thread.sleep(4000);
		logger.info("leave getEmployeeAddresses()");
		return CompletableFuture.completedFuture(addresses);
		
	}
	@Async("asyncExecutor")
	public CompletableFuture<EmployeeNames> getEmployeeNames() throws InterruptedException{
		logger.info("enter getEmployeeNames()");
		EmployeeNames names =  restTemplate.getForObject("http://localhost:8080/getNames", EmployeeNames.class);
		logger.info("EmployeeNames {} ", names);
		Thread.sleep(4000);
		logger.info("leave getEmployeeNames()");
		return CompletableFuture.completedFuture(names);
		
	}
	@Async("asyncExecutor")
	public CompletableFuture<EmployeePhone> getEmployeePhones() throws InterruptedException{
		logger.info("enter getEmployeePhones()");
		EmployeePhone phone = restTemplate.getForObject("http://localhost:8080/getPhones", EmployeePhone.class);
		logger.info("EmployeePhone {}", phone);
		Thread.sleep(4000);
		logger.info("leave getEmployeePhones()");
		return CompletableFuture.completedFuture(phone);
		
	}

}
