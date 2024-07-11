package com.bachar.testing.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerRegistrationService {

    private final CustomerRepository repository;

//    public void registerNewCustomer(CustomerRegistrationRequest request) {
//        if (repository.existsCustomerByPhoneNumber(request.phoneNumber())) {
//            throw new IllegalArgumentException("Customer already exists with phone number: " + request.phoneNumber());
//        }
//        Customer customer = Customer.builder().name(request.name()).phoneNumber(request.phoneNumber()).build();
//        repository.save(customer);
//    }
    public void registerNewCustomer(CustomerRegistrationRequest request) {
        //1. Check if PhoneNumber is taken
        //2. if taken lets check if belongs to same customer
        //2.1 if yes return
        //2.2 throw an exception
        //3. Save customer

    }

}
