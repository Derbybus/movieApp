package com.rendShow.customerService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Subscriptions {
    private Long planId;
    private String subscriptionType;
    private Integer usersAllowed;

}
