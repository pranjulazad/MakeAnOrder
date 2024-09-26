package com.welcome.makeanorder.repository;

import com.welcome.makeanorder.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller, Long> {
    boolean existsByEmailOrMobile(String email, String mobile);
}
