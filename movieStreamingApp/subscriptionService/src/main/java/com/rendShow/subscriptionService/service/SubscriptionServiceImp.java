package com.rendShow.subscriptionService.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


import com.rendShow.subscriptionService.dto.Customers;
import com.rendShow.subscriptionService.dto.ResponseTemplate;
import com.rendShow.subscriptionService.pojo.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rendShow.subscriptionService.Repository.SubscriptionRepository;
//import com.rendShow.subscriptionService.config.WebClientConfig;
import com.rendShow.subscriptionService.pojo.Subscriptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SubscriptionServiceImp implements SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
//	@Autowired
//	private WebClientConfig webClientConfig;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Subscriptions createSubscription(Subscriptions subscriptions) {
		////		int userAllowed = 5;
////		int usersTryingToAccess = subscriptions.getUsersAllowed();
////		if(usersTryingToAccess > userAllowed){
////			throw new AccessDeniedException("Maximum number of users exceeded.");
////		}
//
		// Gets the price of the subscription
		double price = subscriptions.getPrice();
		// Gets the subscription type as a string
		String subscriptionType = String.valueOf(subscriptions.getSubscriptionType());
		// Calculates the price of the subscription based on the subscription type
		switch (subscriptionType) {
			case "Basic" -> price += 100;
			case "Standard" -> price += 150;
			case "Premium" -> price += 200;
		}

		// Creates a new Subscriptions object with the updated price and subscription date
        Subscriptions subscription = Subscriptions.builder()
				.customerId(subscriptions.getCustomerId())
				.subscriptionType(SubscriptionType.valueOf(subscriptionType))
				.price(price)
				.subscriptionDate(subscriptions.getSubscriptionDate())
				.usersAllowed(subscriptions.getUsersAllowed())
				.nextRenewalDate(calculateNextRenewalDate())
				.planValidity(subscriptions.getPlanValidity())
				.build();

		return subscriptionRepository.save(subscription);
	}

	//Declares a private method that returns a local date object
	private LocalDate calculateEndDate() {
		//sets the plan validity to 30 days
		int planValidity = 30;
		//gets current date as the subscription date
		LocalDate subscriptionDate = LocalDate.now();
		//adds the plan validity in months to the subscription date and returns the resulting local date object
		return (LocalDate) subscriptionDate.plusMonths(planValidity);
	}

	//calculates the date that the subscription should be renewed one month from the current date.
	private Date calculateNextRenewalDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();

	}

    public Subscriptions getSubscriptionDetails(Long planId) {
		return subscriptionRepository.findById(planId)
				.stream()
				.filter(subscriptions -> Objects.equals(subscriptions.getPlanId(), planId))
				.findAny()
				.orElseThrow();
    }

	public ResponseTemplate getUserWithSubscriptions(Long planId) {
		ResponseTemplate vo = new ResponseTemplate();
		Subscriptions subscriptions = subscriptionRepository.findByPlanId(planId);

		Customers customers = restTemplate.getForObject("http://localhost:1001/api/customer/" + subscriptions.getCustomerId(), Customers.class);
		vo.setSubscriptions(subscriptions);
		vo.setCustomers(customers);
		return vo;

	}


//	@Override
//	public Subscriptions getSubscriptionById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Subscriptions> getAllSubscriptions() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Subscriptions updateSubscription(Subscriptions subscription) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteSubscriptionById(Long id) {
//		// TODO Auto-generated method stub
//
//	}

//	@Override
//	  public List<Subscriptions> getSubscriptionsByType(String subscriptionType) {
//	    return subscriptionRepository.findBySubscriptionType(subscriptionType);
//	  }



//	  
//	    public boolean validateInput(Subscriptions subscription) {
//	        // code to validate the user's input
//	        return isValid;
//	    }
//
//	  
//	  public Subscriptions getPlanDetails(Long planId) {
//	        // code to retrieve the plan details from the database
//		  Subscriptions plan = getPlanDetails(planId);
//		    if(validateInput(subscription) && isPlanAvailable(planId)){
//		        Long subscriptionId = createSubscription(subscription, userId);
//		        sendConfirmationEmail(email, subscription);
//		    }
//	        return plan;
//	    }
}
