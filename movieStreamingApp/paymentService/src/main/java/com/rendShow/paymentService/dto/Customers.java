package com.rendShow.paymentService.dto;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customers {
	
    @Id
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String password;


}
