package com.welcome.makeanorder.repository;

import com.welcome.makeanorder.model.SellerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerAddressRepo extends JpaRepository<SellerAddress, Long> {
}
