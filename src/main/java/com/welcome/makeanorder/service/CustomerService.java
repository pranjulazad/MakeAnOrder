package com.welcome.makeanorder.service;

import com.welcome.makeanorder.controller.customexception.NoCustomerFoundException;
import com.welcome.makeanorder.enums.MessageStatusType;
import com.welcome.makeanorder.helper.APIResponse;
import com.welcome.makeanorder.helper.ServiceHelper;
import com.welcome.makeanorder.model.CustomerAddress;
import com.welcome.makeanorder.model.Customer;
import com.welcome.makeanorder.repository.CustomerAddressRepo;
import com.welcome.makeanorder.repository.CustomerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CustomerAddressRepo addressRepo;

    public List<Customer> getCustomers(){
        return customerRepo.findAll();
    }

    public ResponseEntity<Map<String, String>> addCustomer(Customer customer) {
        boolean isCustomerExistWithEmail = customerRepo.existsByEmailOrMobile(customer.getEmail(), customer.getMobile());

        if (isCustomerExistWithEmail){
            return APIResponse.prepareHash(HttpStatus.BAD_REQUEST, MessageStatusType.FAIL, "Customer Already Exist with the provided email or mobile number");
        }

        customerRepo.save(customer);

        return APIResponse.prepareHash(HttpStatus.CREATED, MessageStatusType.SUCCESS, "Customer Added");
    }

    public Customer getcustomer(long id) throws NoCustomerFoundException {
        Customer customer = customerRepo.findById(id).orElse(null);

        if (customer == null){
            throw new NoCustomerFoundException();
        }
        return customer;
    }

    public ResponseEntity<Map<String, String>> addAddress(@Valid CustomerAddress address, long customer_id) throws NoCustomerFoundException {
        if (!customerRepo.existsById(customer_id)){
            throw new NoCustomerFoundException();
        }

        if(!ServiceHelper.isValidAddressType(address.getAddressType())){
            return APIResponse.prepareHash(HttpStatus.BAD_REQUEST, MessageStatusType.FAIL, String.format("%s not supported", address.getAddressType()));
        }

        address.setCustomerId(customer_id);
        addressRepo.save(address);
        return APIResponse.prepareHash(HttpStatus.CREATED, MessageStatusType.SUCCESS, "Address added to customer");
    }
}
