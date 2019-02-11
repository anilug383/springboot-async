package com.example.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeAddress;
import com.example.demo.model.EmployeeNames;
import com.example.demo.model.EmployeePhone;

@RestController
public class AsyncController {
	
	private static Logger log = LoggerFactory.getLogger(AsyncController.class);
	
	@Autowired
	private AsyncService asyncService;
	
	@GetMapping("/testAsync")
	public void asyncTest() throws InterruptedException, ExecutionException {
		log.info("testAsynch Start");
		CompletableFuture<EmployeeAddress> address = asyncService.getEmployeeAddresses();
		CompletableFuture<EmployeeNames> names = asyncService.getEmployeeNames();
		CompletableFuture<EmployeePhone> phone = asyncService.getEmployeePhones();
		
		CompletableFuture.allOf(address, names, phone).join();
		
		log.info("EmployeeAddress -> "+address.get());
		log.info("---------------------------------");
		log.info("EmployeeNames -> "+names.get());
		log.info("---------------------------------");
		log.info("EmployeePhone -> "+phone.get());
	}

}
