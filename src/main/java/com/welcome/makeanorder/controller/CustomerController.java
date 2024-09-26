package com.welcome.makeanorder.controller;

import com.welcome.makeanorder.controller.customexception.NoCustomerFoundException;
import com.welcome.makeanorder.model.CustomerAddress;
import com.welcome.makeanorder.model.Customer;
import com.welcome.makeanorder.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class CustomerController extends ApiController{
    @Autowired
    CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<Map<String, String>> addCustomer(@RequestBody @Valid Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomerList(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer returnCustomerById(@PathVariable long id) throws NoCustomerFoundException {
        return customerService.getcustomer(id);
    }

    @PostMapping("/customers/{id}/address")
    public ResponseEntity<Map<String, String>> addCustomerAddress(@RequestBody @Valid CustomerAddress address, @PathVariable long id) throws NoCustomerFoundException {
        return customerService.addAddress(address, id);
    }
}
