package com.rendShow.paymentHistoryService.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentHistory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long historyId;
	private String skuCode;
	private LocalDate startDate;
	private LocalDate endDate;
	private String paymentReceipt;
	

}
