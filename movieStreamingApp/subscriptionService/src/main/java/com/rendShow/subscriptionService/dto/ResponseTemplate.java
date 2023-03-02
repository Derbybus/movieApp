package com.rendShow.subscriptionService.dto;

import com.rendShow.subscriptionService.pojo.Subscriptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {
    private Customers customers;
    private Subscriptions subscriptions;
}
