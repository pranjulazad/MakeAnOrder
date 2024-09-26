package com.welcome.makeanorder.controller;

import com.welcome.makeanorder.controller.customexception.NoSellerFoundException;
import com.welcome.makeanorder.model.Product;
import com.welcome.makeanorder.model.SellerAddress;
import com.welcome.makeanorder.model.Seller;
import com.welcome.makeanorder.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class SellerController extends ApiController{

    @Autowired
    SellerService sellerService;

    @GetMapping("/sellers")
    public List<Seller> returnAllSeller(){
        return sellerService.getAllSellers();
    }

    @GetMapping("/sellers/{id}")
    public Seller returnSellerById(@PathVariable long id) throws NoSellerFoundException {
        return sellerService.getSeller(id);
    }

    @GetMapping("/sellers/{id}/products")
    public List<Product> addSellerProduct(@PathVariable long id) throws NoSellerFoundException {
        return sellerService.getProducts(id);
    }

    @PostMapping("/sellers")
    public ResponseEntity<Map<String, String>> addSeller(@RequestBody @Valid Seller seller){
        return sellerService.addSeller(seller);
    }

    @PostMapping("/sellers/{id}/address")
    public ResponseEntity<Map<String, String>> addSellerAddress(@RequestBody @Valid SellerAddress sellerAddress, @PathVariable long id) throws NoSellerFoundException {
        return sellerService.addAddress(sellerAddress, id);
    }

    @PostMapping("/sellers/{id}/product")
    public ResponseEntity<Map<String, String>> addSellerProduct(@RequestBody @Valid Product product, @PathVariable long id) throws NoSellerFoundException {
        return sellerService.addProduct(product, id);
    }
 }
