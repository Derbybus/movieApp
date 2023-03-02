package com.rendShow.paymentHistoryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rendShow.paymentHistoryService.service.HistoryService;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
	
	@Autowired
	private HistoryService historyService;
	
//	@GetMapping("/{sku-code}")
//	public boolean historyData(@PathVariable("sku-code") String skuCode) {
//		return historyService.historyData(skuCode);
//
//	}

}
