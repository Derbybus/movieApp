package com.rendShow.customerService.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rendShow.customerService.pojo.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {


    Optional<Customers> findByEmail(String email);





}
