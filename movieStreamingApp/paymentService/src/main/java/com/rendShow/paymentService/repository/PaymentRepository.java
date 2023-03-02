package com.rendShow.paymentService.repository;

import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rendShow.paymentService.pojo.Payments;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {


}
