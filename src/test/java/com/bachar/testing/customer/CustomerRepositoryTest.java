package com.bachar.testing.customer;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import java.util.Optional;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;


    @Test
    void itShouldFindCustomerByPhoneNumber() {
        //Given
        CustomerRegistrationRequest request = new CustomerRegistrationRequest("Bachar", "123456");
        Customer customerSaved = underTest.saveAndFlush(Customer.builder().name(request.name()).phoneNumber(request.phoneNumber()).build());
        //When
        Optional<Customer> customerByPhoneNumber = underTest.findCustomerByPhoneNumber(customerSaved.getPhoneNumber());
        //Then
        assertThat(customerByPhoneNumber).isPresent().hasValueSatisfying(customer -> {
            assertThat(customer.getPhoneNumber()).isEqualTo(customerSaved.getPhoneNumber());
            assertThat(customer.getName()).isEqualTo(customerSaved.getName());
            assertThat(customer.getId()).isEqualTo(customerSaved.getId());
        });
    }

    @Test
    void itShouldNotFindNotExistingCustomer() {
        //Given
        String phoneNumber = "123456";
        //When
        assertThat(underTest.findCustomerByPhoneNumber(phoneNumber)).isNotPresent();
        //Then
    }

    @Test
    void itShouldSaveCustomer() {
        //Given
        CustomerRegistrationRequest request = new CustomerRegistrationRequest("Bachar", "123456");
        //When
        Customer customerSaved = underTest.saveAndFlush(Customer.builder().name(request.name()).phoneNumber(request.phoneNumber()).build());
        //Then
        Optional<Customer> customer = underTest.findById(customerSaved.getId());

        assertThat(customer).isPresent().hasValueSatisfying(c-> {
            assertThat(c.getName()).isEqualTo(request.name());
            assertThat(c.getPhoneNumber()).isEqualTo(request.phoneNumber());
            assertThat(c.getId()).isEqualTo(customerSaved.getId());
//  Deprecated   assertThat(c).isEqualToComparingFieldByField(customerSaved);
        });
    }

    @Test
    void itShouldNotSaveCustomerWithNullName() {
        //Given
        CustomerRegistrationRequest request = CustomerRegistrationRequest.builder().name(null).phoneNumber("123456").build();
        //When
        assertThatThrownBy(() -> underTest.save(new Customer(request.name(), request.phoneNumber()))).isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must not be blank");
        }

    @Test
    void itShouldNotSaveCustomerWithNullPhoneNumber() {
        //Given
        CustomerRegistrationRequest request = CustomerRegistrationRequest.builder().name("Bachar").phoneNumber(null).build();
        //When
        assertThatThrownBy(() -> underTest.save(new Customer(request.name(), request.phoneNumber()))).isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must not be blank");
    }
}