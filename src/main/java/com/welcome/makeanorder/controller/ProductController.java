package com.welcome.makeanorder.controller;

import com.welcome.makeanorder.model.Product;
import com.welcome.makeanorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class ProductController extends ApiController{
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Map<String, String>> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getListOfProducts(){
        return productService.getAllProducts();
    }
}
