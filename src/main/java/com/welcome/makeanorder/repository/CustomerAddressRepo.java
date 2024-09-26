package com.welcome.makeanorder.repository;

import com.welcome.makeanorder.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, Long> {
}
