package com.rendShow.paymentHistoryService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rendShow.paymentHistoryService.pojo.PaymentHistory;

@Repository
public interface HistoryRepository extends JpaRepository<PaymentHistory, Long> {

	//Optional<PaymentHistory> findBySkuCode();

}
