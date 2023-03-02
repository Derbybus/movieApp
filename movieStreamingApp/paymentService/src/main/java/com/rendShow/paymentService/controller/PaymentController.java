package com.rendShow.paymentService.controller;

import com.rendShow.paymentService.pojo.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rendShow.paymentService.service.PaymentServiceImp;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	@Autowired
	private PaymentServiceImp paymentService;
	
	@PostMapping
	public Payments makePayment(@RequestBody Payments payments) {
		return paymentService.processPayment(payments);

	}

	@GetMapping("/{id}")
	public Payments getPaymentDetailsWithUser(@PathVariable("id") Long paymentId){
		return paymentService.getPaymentDetails(paymentId);
	}

}
