package com.welcome.makeanorder.service;

import com.welcome.makeanorder.enums.MessageStatusType;
import com.welcome.makeanorder.helper.ServiceHelper;
import com.welcome.makeanorder.model.Product;
import com.welcome.makeanorder.repository.ProductRepo;
import com.welcome.makeanorder.repository.SellerRepo;
import com.welcome.makeanorder.helper.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    SellerRepo sellerRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public ResponseEntity<Map<String, String>> addProduct(Product product) {

        if(!ServiceHelper.isValidProductType(product.getProductType())){
            return APIResponse.prepareHash(HttpStatus.BAD_REQUEST, MessageStatusType.FAIL, String.format("%s not supported", product.getProductType()));
        }

        if(!sellerRepo.existsById(product.getSellerId())){
            return APIResponse.prepareHash(HttpStatus.BAD_REQUEST, MessageStatusType.FAIL, "Seller does not exist");
        }

        // Saving the product, if any field is invalid it throws validation exception
        productRepo.save(product);

        return APIResponse.prepareHash(HttpStatus.CREATED, MessageStatusType.SUCCESS, "Product saved");
    }
}
