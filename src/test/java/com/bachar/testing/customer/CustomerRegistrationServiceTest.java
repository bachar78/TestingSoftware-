package com.bachar.testing.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerRegistrationServiceTest {


    @Mock
    private CustomerRepository customerRepository;

    private CustomerRegistrationService underTest;
    private AutoCloseable autoCloseable;




    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerRegistrationService(customerRepository);
    }

    @Test
    void itShouldSaveNewCustomer() {
        //Given
        CustomerRegistrationRequest request = CustomerRegistrationRequest.builder().name("Bachar").phoneNumber("123456").build();
        ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);

        when(customerRepository.findCustomerByPhoneNumber(request.phoneNumber())).thenReturn(Optional.empty());
        //When
        underTest.registerNewCustomer(request);
        //Then
        verify(customerRepository).save(argumentCaptor.capture());
        Customer customer = argumentCaptor.getValue();
        assertThat(customer.getName()).isEqualTo("Bachar");
        assertThat(customer.getPhoneNumber()).isEqualTo("123456");
    }

    @Test
    void itShouldNotSaveCustomerWhenCustomerExists() {
        //Given
        CustomerRegistrationRequest request = CustomerRegistrationRequest.builder().name("Bachar").phoneNumber("123456").build();
        Customer customer = Customer.builder().name("Bachar").phoneNumber("123456").build();
        when(customerRepository.findCustomerByPhoneNumber(request.phoneNumber())).thenReturn(Optional.of(customer));
        //When
        underTest.registerNewCustomer(request);
        //Then
//        verify(customerRepository).findCustomerByPhoneNumber(request.phoneNumber());
        verify(customerRepository, never()).save(any(Customer.class));
//        verifyNoMoreInteractions(customerRepository);
    }
}