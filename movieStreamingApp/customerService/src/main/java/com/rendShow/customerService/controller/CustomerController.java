package com.rendShow.customerService.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;


import com.rendShow.customerService.config.WebClientConfig;
import com.rendShow.customerService.dto.AuthenticationRequest;
import com.rendShow.customerService.dto.AuthenticationResponse;
import com.rendShow.customerService.dto.Subscriptions;
import com.rendShow.customerService.dto.UserModel;
import com.rendShow.customerService.exception.ExceededDeviceException;
import com.rendShow.customerService.pojo.Device;
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
//@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService service;

	@Autowired
	private WebClientConfig webClientConfig;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> registerCustomer(@RequestBody UserModel userModel) {
		return ResponseEntity.ok(service.createCustomer(userModel));
		
	}

	private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request, String email, String deviceId){
		AuthenticationResponse response = service.authenticate(request);
		if (response.isSuccess()){
			boolean success = service.connectDevice(email, deviceId);
			if (success){
				LOGGER.info("Device connected for user " + email);
			}
		}
		return ResponseEntity.ok(response);
	}

//	@PostMapping("/authenticate")
//	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//		AuthenticationResponse response = service.authenticate(request);
//		if (response.isSuccess()) {
//			String email = request.getEmail();
//			String deviceId = request.getDeviceId();
//			Customers customer = service.getCustomerByEmail(email);
//			if (customer.getDevices().size() < 5) {
//				Device device = new Device();
//				device.setCustomers(customer);
//				device.setLastConnected(LocalDateTime.now());
//				customer.getDevices().add(device);
//				customerRepository.save(customer);
//				LOGGER.info("Device connected for user " + email);
//			} else {
//				throw new ExceededDeviceException("Error: User has already connected 5 devices");
//			}
//		}
//		return ResponseEntity.ok(response);
//	}




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
	public ResponseEntity<?> getCustomerName(@PathVariable("name") String email){
		Customers customer = service.getCustomerByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}

	@GetMapping
	public List<Customers> getAllCustomers(){

		return customerRepository.findAll();
	}

//	@GetMapping("/device/{id}")
//	public ResponseEntity<Boolean> deviceConnected(@PathVariable("id") String deviceId, String email) {
//		boolean device = service.connectDevice(email, deviceId);
//		return ResponseEntity.ok(device);
//	}

	@GetMapping("/devices")
	public ResponseEntity<List<String>> getRegisteredDevices(String email) {
		List<String> devices = service.getUserDevices(email);
		return ResponseEntity.ok(devices);
	}


//	@GetMapping("/device/{id}")
//	public ResponseEntity<?> deviceConnected(@PathVariable("id") String deviceId, String email){
//		boolean device = service.connectDevice(email,deviceId);
//		return ResponseEntity.status(HttpStatus.OK).body(device);
//
//	}

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
