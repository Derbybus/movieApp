package com.rendShow.subscriptionService.service;

import java.time.LocalDate;
import java.util.List;

import com.rendShow.subscriptionService.dto.ResponseTemplate;
import com.rendShow.subscriptionService.pojo.Subscriptions;

public interface SubscriptionService {
	
	Subscriptions createSubscription(Subscriptions subscriptions);
	ResponseTemplate getUserWithSubscriptions(Long planId);
//	Subscriptions getSubscriptionById(Long id);
//	List<Subscriptions> getAllSubscriptions();
//	Subscriptions updateSubscription(Subscriptions subscription);
//	void deleteSubscriptionById(Long id);
//	List<Subscriptions> getSubscriptionsByType(String subscriptionType);
//	List<Subscriptions> getSubscriptionsByPriceRange(Double minPrice, Double maxPrice);
//	List<Subscriptions> getSubscriptionsByDateRange(LocalDate startDate, LocalDate endDate);

}
