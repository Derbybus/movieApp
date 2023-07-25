package com.rendShow.customerService.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String deviceId = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;
    private LocalDateTime lastConnected;



}
