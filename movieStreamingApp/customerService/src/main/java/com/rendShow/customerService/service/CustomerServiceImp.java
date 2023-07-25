package com.rendShow.customerService.service;

import java.util.*;
import java.util.stream.Collectors;

//import com.rendShow.customerService.config.WebClientConfig;
import com.rendShow.customerService.config.JwtService;
import com.rendShow.customerService.dto.AuthenticationRequest;
import com.rendShow.customerService.dto.AuthenticationResponse;
import com.rendShow.customerService.dto.UserModel;
import com.rendShow.customerService.exception.CustomerNotFoundException;
import com.rendShow.customerService.exception.ExceededDeviceException;
import com.rendShow.customerService.exception.IdNotFoundException;
import com.rendShow.customerService.pojo.*;
import com.rendShow.customerService.repository.DeviceRepository;
import com.rendShow.customerService.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rendShow.customerService.repository.CustomerRepository;


@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	public DeviceRepository deviceRepository;


//	@Autowired
//	private WebClientConfig webClientConfig;


	@Override
	public AuthenticationResponse createCustomer(UserModel userModel) {
		Customers customer = Customers.builder()
				.firstName(userModel.getFirstName())
				.lastName(userModel.getLastName())
				.phoneNo(userModel.getPhoneNo())
				.email(userModel.getEmail())
				.password(passwordEncoder.encode(userModel.getPassword()))
				.address(userModel.getAddress())
				.role(Role.USER)
				.build();
		var savedUser = customerRepository.save(customer);
		var jwtToken = jwtService.generateToken(customer);
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();

	}



	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				)
		);
		Customers customer = customerRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new CustomerNotFoundException("Email not found"));
		var jwtToken = jwtService.generateToken(customer);
		revokeAllUserTokens(customer);
		saveUserToken(customer, jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	private void revokeAllUserTokens(Customers customers){
		var validToken = tokenRepository.findAllValidTokensByUser(customers.getId());
		if (validToken.isEmpty())
			return;
			validToken.forEach(t -> {
				t.setExpired(true);
				t.setRevoked(true);
			});
			tokenRepository.saveAll(validToken);
	}

	private void saveUserToken(Customers customers, String jwtToken) {
		var token = Token.builder()
				.customers(customers)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.revoked(false)
				.expired(false)
				.build();
		tokenRepository.save(token);
	}



	@Override
	public Customers getCustomerById(Long id) {
		return customerRepository.findById(id)
				.stream()
				.filter(customer -> customer.getId() == id)
				.findAny()
				.orElseThrow(() -> new IdNotFoundException("Id not found"));
	}

	@Override
	public Customers getCustomerByEmail(String email) {
		return customerRepository.findAll()
				.stream()
				.filter(customer -> customer.getEmail().equals(email))
				.findAny()
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
	}

	private final Map<String, Set<String>> userDevicesMap = new HashMap<>();
	@Override
	public boolean connectDevice(String email, String deviceId) {
		if(userDevicesMap.containsKey(email)){
			Set<String> devices = userDevicesMap.get(email);
			if(devices.size() >=5){
				throw new ExceededDeviceException("Error: User has already connected 5 devices");
			}
			devices.add(deviceId);
		} else {
			//First time user is connecting a device, add to map
			Set<String> devices = new HashSet<>();
			devices.add(deviceId);
			userDevicesMap.put(email, devices);
		}
		return true;
	}


	@Override
	public List<String> getUserDevices(String email) {
		List<Device> devices = deviceRepository.findByUserEmail(email);
		return devices.stream().map(Device::getDeviceId).collect(Collectors.toList());
	}




}


