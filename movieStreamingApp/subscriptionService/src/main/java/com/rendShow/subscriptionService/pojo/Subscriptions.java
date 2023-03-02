package com.rendShow.subscriptionService.pojo;



import java.time.LocalDate;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Subscriptions {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long planId;
	private Long customerId;
	@Enumerated(EnumType.STRING)
	private SubscriptionType subscriptionType;
	private Double price = 0.0;
	private LocalDate subscriptionDate = LocalDate.now();
	private Integer usersAllowed;
    private Integer planValidity =30;
	private Date nextRenewalDate;


}
