//package com.bachar.testing.customer;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
////This disconnect from the original database
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//class CustomerRepositoryTest {
//
//    @Autowired
//    private CustomerRepository underTest;
//
//    @Test
//    void itShouldSelectCustomerByPhoneNumber() {
//        //Given
//        Customer bachar = underTest.saveAndFlush(Customer.builder().name("bachar").phoneNumber("123456").build());
//        //When
//        Optional<Customer> customerByPhoneNumber = underTest.findCustomerByPhoneNumber("123456");
//        //Then
//        assertThat(customerByPhoneNumber)
//                .isPresent()
//                .hasValueSatisfying(customer -> {
//                    assertThat(customer.getPhoneNumber()).isEqualTo(bachar.getPhoneNumber());
//                    assertThat(customer.getId()).isEqualTo(bachar.getId());
//                    assertThat(customer.getName()).isEqualTo(bachar.getName());
//                });
//    }
//
//    @Test
//    void whenExistsCustomerByPhoneNumberIsTrue() {
//        //Given
//        Customer bachar = underTest.saveAndFlush(Customer.builder().name("bachar").phoneNumber("123456").build());
//        //When
//        boolean existBachar = underTest.existsCustomerByPhoneNumber(bachar.getPhoneNumber());
//        //Then
//        assertThat(existBachar).isTrue();
//    }
//
//    @Test
//    void whenExistsCustomerByPhoneNumberIsFalse() {
//        //When
//        boolean existBachar = underTest.existsCustomerByPhoneNumber(UUID.randomUUID().toString());
//        assertThat(existBachar).isFalse();
//    }
//
//    @Test
//    void canNotSaveTwoCustomersWithSamePhoneNumber() {
//        //Given
//        Customer bachar = underTest.saveAndFlush(Customer.builder().name("bachar").phoneNumber("123456").build());
//        Customer samer = new Customer("samer", "123456");
//        //When
//       assertThatThrownBy(()->
//               underTest.saveAndFlush(samer)).isInstanceOf(DataIntegrityViolationException.class);
//    }
//}