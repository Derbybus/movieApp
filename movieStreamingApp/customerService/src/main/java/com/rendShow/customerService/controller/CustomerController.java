package com.rendShow.customerService.controller;

import java.util.List;


import com.rendShow.customerService.config.WebClientConfig;
import com.rendShow.customerService.dto.Subscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.rendShow.customerService.pojo.Customers;
import com.rendShow.customerService.repository.CustomerRepository;
import com.rendShow.customerService.service.CustomerService;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;

	@Autowired
	private WebClientConfig webClientConfig;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customers customer) {
		Customers customers = service.createCustomer(customer);
		if(customers != null){
			return new ResponseEntity<String>("user exists", HttpStatus.OK);
		}

		customerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body("Customer successfully saved");
		
	}

	//saves the subscription object
//	@PostMapping("/customer-subscription")
//	public Subscriptions customerSubscription(@RequestBody Subscriptions subscriptionDto ){
//
//		Subscriptions sub = Subscriptions.builder()
//				.email(subscriptionDto.getEmail())
//				.subscriptionType(subscriptionDto.getSubscriptionType())
//				.usersAllowed(subscriptionDto.getUsersAllowed())
//				.build();
//		//sends the new subscription to the subscription API
//		return webClientConfig.webClientBuilder()
//				.build()
//				.post()
//				//send the request to the post method in subscription service
//				.uri("http://subscription-service/api/subscription/create")
//				//.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.body(Mono.just(sub), Subscriptions.class)
//				.retrieve()
//				.bodyToMono(Subscriptions.class)
//				.block();
//	}
//



	@GetMapping("/{id}")
	public Customers getCustomerId(@PathVariable("id") Long id){
		return service.getCustomerById(id);

	}

	
	
	@GetMapping("/name/{name}")
	public ResponseEntity<?> getCustomerName(@PathVariable("name") String name){
		Customers customer = service.getCustomerByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}

	@GetMapping
	public List<Customers> getAllCustomers(){

		return customerRepository.findAll();
	}

//	@PutMapping("/update/{id}")
//	public Mono<Customers> updateCustomers(@RequestBody Customers customers){
//		Customers customer = Customers.builder().build();
//
//		return webClientConfig.webClientBuilder()
//				.build()
//				.put()
//				.uri("http://localhost:1001/api/customer/")
//				.body(Customers.class)
//				.retrieve()
//				.bodyToMono(Customers.class)
//				.block();
//	}
}
