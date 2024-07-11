//package com.bachar.testing.customer;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.then;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.when;
//
//class CustomerRegistrationServiceTest {
//
//    @Mock
//    private CustomerRepository repository;
//    private CustomerRegistrationService underTest;
//    private AutoCloseable autoCloseable;
//
//
//    @BeforeEach
//    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
//        underTest = new CustomerRegistrationService(repository);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }
//
//    @Test
//    void registerNewCustomerHappyPath() {
//        //Given
//        CustomerRegistrationRequest request = CustomerRegistrationRequest.builder()
//                .name("bachar").phoneNumber("123456").build();
//        when(repository.existsCustomerByPhoneNumber(request.phoneNumber())).thenReturn(false);
//        //when
//        underTest.registerNewCustomer(request);
//        //Then
//        ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);
//        then(repository).should().save(argumentCaptor.capture());
//        Customer customer = argumentCaptor.getValue();
//        assertThat(customer.getName()).isEqualTo(request.name());
//        assertThat(customer.getPhoneNumber()).isEqualTo(request.phoneNumber());
//    }
//
//    @Test
//    void registerNewCustomerThrowsException() {
//        //Given
//        CustomerRegistrationRequest request = CustomerRegistrationRequest.builder()
//                .name("bachar").phoneNumber("123456").build();
//        when(repository.existsCustomerByPhoneNumber(request.phoneNumber())).thenReturn(true);
//        //Then
////        then(repository).shouldHaveNoMoreInteractions();
//        assertThatThrownBy(() -> underTest.registerNewCustomer(request))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Customer already exists with phone number:");
//        then(repository).should(never()).save(any(Customer.class));
//
//    }
//}
