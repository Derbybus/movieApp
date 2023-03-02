package com.rendShow.paymentHistoryService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rendShow.paymentHistoryService.repository.HistoryRepository;

@Service
public class HistoryService {
	
	@Autowired
	private HistoryRepository historyRepository;
	
//	@Transactional(readOnly = true)
//	public boolean historyData(String skuCode) {
//		return historyRepository.findBySkuCode().isPresent();
//
//	}

}
