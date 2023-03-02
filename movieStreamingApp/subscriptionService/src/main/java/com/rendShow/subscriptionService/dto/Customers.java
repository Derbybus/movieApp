package com.rendShow.subscriptionService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customers {

	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String password;
	


}
