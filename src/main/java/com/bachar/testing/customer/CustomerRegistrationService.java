package com.bachar.testing.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerRegistrationService {

    private final CustomerRepository repository;

    public void registerNewCustomer(CustomerRegistrationRequest request) {
        //1. Check if PhoneNumber is taken
        String phoneNumber = request.phoneNumber();
        Optional<Customer> customerOptional = repository.findCustomerByPhoneNumber(phoneNumber);
        if (customerOptional.isPresent()) {
          //  if taken lets check if belongs to same customer
            Customer customer = customerOptional.get();
            if(customer.getName().equals(request.name())) {
                return;
            }
            throw new IllegalStateException(String.format("Phone number %s already taken", request.phoneNumber()));
        }
        Customer customer = Customer.builder().name(request.name()).phoneNumber(phoneNumber).build();
        //3. Save customer
        repository.save(customer);
    }

}
