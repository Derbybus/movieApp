package com.rendShow.customerService.dto;

import com.rendShow.customerService.pojo.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String password;
    private String matchingPassword;
    @Embedded
    private Address address;
}
