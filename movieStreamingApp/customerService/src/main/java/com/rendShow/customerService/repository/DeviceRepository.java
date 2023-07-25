package com.rendShow.customerService.repository;

import com.rendShow.customerService.pojo.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeviceRepository extends JpaRepository<Device,String> {
    @Query("SELECT d FROM Device d JOIN d.customers c WHERE c.email = :email")
    List<Device> findByUserEmail(String email);
}
