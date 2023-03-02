package com.rendShow.paymentHistoryService.pojo;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentHistory {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long historyId;
	private String skuCode;
	private LocalDate startDate;
	private LocalDate endDate;
	private String paymentReceipt;
	

}
