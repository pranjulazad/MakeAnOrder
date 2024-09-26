package com.welcome.makeanorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class OrderController extends ApiController{

    @GetMapping("/orders")
    public List<String> orders(){
        ArrayList<String> orders = new ArrayList<>();
        orders.add("a");
        orders.add("b");
        return orders;
    }
}
