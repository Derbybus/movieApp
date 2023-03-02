package com.rendShow.customerService.service;

import java.util.List;
import java.util.Optional;

//import com.rendShow.customerService.config.WebClientConfig;
import com.rendShow.customerService.dto.Subscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.rendShow.customerService.pojo.Customers;
import com.rendShow.customerService.repository.CustomerRepository;


@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

//	@Autowired
//	private WebClientConfig webClientConfig;


	@Override
	public Customers createCustomer(Customers customers) {
		Customers newCustomers = new Customers();
		newCustomers.setEmail(customers.getEmail());
		newCustomers.setPassword(customers.getPassword());
		Example<Customers> example = Example.of(newCustomers);
		Optional<Customers> optional = customerRepository.findOne(example);
		return optional.orElse(null);
	}

	@Override
	public Customers getCustomerById(Long id) {


		return customerRepository.findById(id)
				.stream()
				.filter(customer -> customer.getId() == id)
				.findAny()
				.orElseThrow();
	}

	@Override
	public Customers getCustomerByName(String name) {
		return customerRepository.findAll()
				.stream()
				.filter(customer -> customer.getFirstName().equals(name) || customer.getLastName().equals(name))
				.findAny()
				.orElseThrow();
	}


}


