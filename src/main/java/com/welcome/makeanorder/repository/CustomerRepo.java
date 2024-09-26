package com.welcome.makeanorder.repository;

import com.welcome.makeanorder.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
   /*@Query("select 1 from customers where email = ?1")*/
   boolean existsByEmailOrMobile(String email, String mobile);
}
