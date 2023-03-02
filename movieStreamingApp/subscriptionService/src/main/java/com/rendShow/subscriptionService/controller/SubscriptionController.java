package com.rendShow.subscriptionService.controller;

import java.util.List;

import com.rendShow.subscriptionService.Repository.SubscriptionRepository;
import com.rendShow.subscriptionService.dto.ResponseTemplate;
import com.rendShow.subscriptionService.pojo.Subscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.rendShow.subscriptionService.service.SubscriptionServiceImp;



@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionServiceImp subscriptionService;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	
	@PostMapping("/create")
	public Subscriptions createSubscription(@RequestBody Subscriptions subscriptions) {
		return subscriptionService.createSubscription(subscriptions);
		
	}

//	@GetMapping("/id/{id}")
//	public Subscriptions getSubscriptionsWithCustomer(@PathVariable("id") Long planId) {
//		return subscriptionService.getSubscriptionDetails(planId);
//	}

	@GetMapping("/{id}")
	public ResponseTemplate getUserSubscriptions(@PathVariable("id") Long planId){
		return subscriptionService.getUserWithSubscriptions(planId);
	}




//	@GetMapping("/subscriptions/{id}")
//	public List<Subscriptions> getSubscriptionsByCustomerId(@PathVariable("id") Long id) {
//		String customerUrl = "http://customer-service/api/customer/" + id;
//		ResponseEntity<CustomerResponse> customerResponse = restTemplate.getForEntity(customerUrl, CustomerResponse.class);
//		if (!customerResponse.getStatusCode().is2xxSuccessful()) {
//			throw new CustomerNotFoundException();
//		}
//		CustomerResponse customer = customerResponse.getBody();
//		List<Subscriptions> subscriptions = subscriptionRepository.findByCustomerId(id);
//		return subscriptions;
//	}




}
