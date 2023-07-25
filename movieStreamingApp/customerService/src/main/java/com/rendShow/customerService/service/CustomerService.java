package com.rendShow.customerService.service;

import java.util.List;


import com.rendShow.customerService.dto.AuthenticationRequest;
import com.rendShow.customerService.dto.AuthenticationResponse;
import com.rendShow.customerService.dto.UserModel;
import com.rendShow.customerService.exception.CustomerNotFoundException;
import com.rendShow.customerService.exception.ExceededDeviceException;
import com.rendShow.customerService.exception.IdNotFoundException;
import com.rendShow.customerService.pojo.Customers;

public interface CustomerService {

	AuthenticationResponse createCustomer(UserModel userModel);

	AuthenticationResponse authenticate(AuthenticationRequest request);
	
//	CustomerResponse findById(Long id);
    Customers getCustomerById(Long id) throws IdNotFoundException;
	
//	CustomerResponse getByName(String name);

	Customers getCustomerByEmail(String email) throws CustomerNotFoundException;

	boolean connectDevice(String email, String deviceId) throws ExceededDeviceException;

	List<String> getUserDevices(String email);


}
