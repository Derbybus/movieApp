package com.rendShow.paymentService.pojo;



import java.time.LocalDate;
import java.util.Date;


import com.rendShow.paymentService.dto.Customers;
import com.rendShow.paymentService.dto.Subscriptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Payments {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long paymentId;
	private Double amountPaid;
	private LocalDate paymentDate = LocalDate.now();
	private Date nextRenewalDate;
	private Long customerId;
	private Long planId;
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;


	public void setCustomers(Customers customer) {
	}

	public void setSubscriptions(Subscriptions subscriptions) {
	}
}
