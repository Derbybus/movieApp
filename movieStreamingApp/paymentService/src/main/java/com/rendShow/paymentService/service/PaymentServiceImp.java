package com.rendShow.paymentService.service;


import java.util.*;

import com.rendShow.paymentService.dto.Customers;
import com.rendShow.paymentService.dto.Subscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rendShow.paymentService.config.WebClientConfig;
import com.rendShow.paymentService.pojo.Payments;
import com.rendShow.paymentService.repository.PaymentRepository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	//@Autowired
	//private WebClientConfig webClientConfig;
	 WebClient webClientConfig;


	@Override
	public Payments processPayment(Payments payment) {

		Payments payments = Payments.builder()
				.paymentMethod(payment.getPaymentMethod())
				.paymentDate(payment.getPaymentDate())
				.amountPaid(payment.getAmountPaid())
				.nextRenewalDate(calculateNextRenewalDate())
				.customerId(payment.getCustomerId())
				.planId(payment.getPlanId())
				.build();





		return paymentRepository.save(payments);

	}


	private Date calculateNextRenewalDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();

	}

	public Payments getPaymentDetails(Long paymentId) {
		// code to retrieve payment from database
//		return paymentRepository.findById(paymentId)
//				.stream()
//				.filter(payments -> Objects.equals(payments.getPaymentId(), paymentId))
//				.findAny()
//				.orElseThrow();
		return paymentRepository.findById(paymentId)
				.stream()
				.filter(payments -> Objects.equals(payments.getPaymentId(), paymentId))
				.findAny()
				.orElseThrow();

//		Customers customer = webClientConfig.get()
//				.uri("http://localhost:1001/api/customers/" + paymentId)
//				.retrieve()
//				.bodyToMono(Customers.class).block();

		// code to retrieve customer from Customers service
//		Customers customer = webClientConfig.webClientBuilder()
//						.build()
//								.get()
//										.uri("http://localhost:1001/api/customer/id{id}" + payment.getCustomerId())
//												.retrieve()
//														.bodyToMono(Customers.class)
//																.block();

		// code to retrieve subscription from Subscriptions service
//		Subscriptions subscriptions = webClientConfig.webClientBuilder()
//				.build()
//				.get()
//				.uri("http://localhost:1003/api/subscription/{id}" + payment.getPlanId())
//				.retrieve()
//				.bodyToMono(Subscriptions.class)
//				.block();

		// attach customer and subscription details to payment object
		//payment.setCustomers(customer);
//		payment.setSubscriptions(subscriptions);

		//return payment;
	}

}


