package com.welcome.makeanorder.service;

import com.welcome.makeanorder.controller.customexception.NoSellerFoundException;
import com.welcome.makeanorder.enums.MessageStatusType;
import com.welcome.makeanorder.helper.APIResponse;
import com.welcome.makeanorder.helper.ServiceHelper;
import com.welcome.makeanorder.model.Product;
import com.welcome.makeanorder.model.SellerAddress;
import com.welcome.makeanorder.model.Seller;
import com.welcome.makeanorder.repository.ProductRepo;
import com.welcome.makeanorder.repository.SellerAddressRepo;
import com.welcome.makeanorder.repository.SellerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SellerService {
    @Autowired
    SellerRepo sellerRepo;

    @Autowired
    SellerAddressRepo sellerAddressRepo;

    @Autowired
    ProductRepo productRepo;

    public List<Seller> getAllSellers(){
        return sellerRepo.findAll();
    }

    public ResponseEntity<Map<String, String>> addSeller(Seller seller){
        boolean isSellerExistWithEmail = sellerRepo.existsByEmailOrMobile(seller.getEmail(), seller.getMobile());

        if (isSellerExistWithEmail){
            return APIResponse.prepareHash(HttpStatus.BAD_REQUEST, MessageStatusType.FAIL, "Seller Already Exist with the provided email or mobile number");
        }

        sellerRepo.save(seller);

        return APIResponse.prepareHash(HttpStatus.CREATED, MessageStatusType.SUCCESS, "Seller Added");
    }

    public Seller getSeller(long id) throws NoSellerFoundException {
        Seller seller = sellerRepo.findById(id).orElse(null);

        if (seller == null){
            throw new NoSellerFoundException();
        }
        return seller;
    }

    public ResponseEntity<Map<String, String>> addAddress(SellerAddress sellerAddress, long sellerId) throws NoSellerFoundException {
        if (!sellerRepo.existsById(sellerId)){
            throw new NoSellerFoundException();
        }

        if(!ServiceHelper.isValidAddressType(sellerAddress.getAddressType())){
            return APIResponse.prepareHash(HttpStatus.BAD_REQUEST, MessageStatusType.FAIL, String.format("%s not supported", sellerAddress.getAddressType()));
        }

        sellerAddress.setSellerId(sellerId);
        sellerAddressRepo.save(sellerAddress);
        return APIResponse.prepareHash(HttpStatus.CREATED, MessageStatusType.SUCCESS, "Address added to seller");
    }

    public ResponseEntity<Map<String, String>> addProduct(@Valid Product product, long sellerId) throws NoSellerFoundException {
        if (!sellerRepo.existsById(sellerId)){
            throw new NoSellerFoundException();
        }

        if(!ServiceHelper.isValidProductType(product.getProductType())){
            return APIResponse.prepareHash(HttpStatus.BAD_REQUEST, MessageStatusType.FAIL, String.format("product type `%s` not supported", product.getProductType()));
        }

        product.setSellerId(sellerId);
        productRepo.save(product);
        return APIResponse.prepareHash(HttpStatus.CREATED, MessageStatusType.SUCCESS, "Product added to seller");
    }

    public List<Product> getProducts(long id) {
        return productRepo.findProductsBySellerId(id);
    }
}
