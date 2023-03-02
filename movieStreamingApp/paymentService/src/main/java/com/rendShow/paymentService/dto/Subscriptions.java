package com.rendShow.paymentService.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscriptions {
	
	@Id
	private Long planId;	
	private String subscriptionType;
	private BigDecimal price;
	private LocalDate subscriptionDate = LocalDate.now();


}
