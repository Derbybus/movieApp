package com.rendShow.subscriptionService.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rendShow.subscriptionService.pojo.Subscriptions;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
    Subscriptions findByPlanId(Long planId);
    // List<Subscriptions> findByCustomerId(String customerId);

//	List<Subscriptions> findBySubscriptionDateBetween(LocalDate startDate, LocalDate endDate);
//
//	List<Subscriptions> findByPriceBetween(Double minPrice, Double maxPrice);
//
//	List<Subscriptions> findBySubscriptionType(String subscriptionType);
//	
//	List<Subscriptions> findByUsersAllowed(Integer usersAllowed);

}
