package com.rendShow.payPal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subscriptions {
    private double price;
    private String currency;
    private String method;
    private String intent;





}
