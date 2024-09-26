package com.welcome.makeanorder.repository;

import com.welcome.makeanorder.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findProductsBySellerId(long id);
}
