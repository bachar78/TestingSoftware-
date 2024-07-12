package com.bachar.testing.payment;

import com.bachar.testing.customer.Customer;
import com.bachar.testing.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository underTest;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void itShouldSavePayment() {
        //Given
        Customer customer = customerRepository.saveAndFlush(new Customer("Bachar", "123456"));
        Payment payment = Payment.builder().customer(customer).amount(10.00)
                .currency(Currency.USD)
                .source("car123")
                .description("Donation")
                .paidAt(LocalDateTime.now())
                .build();
        //When
        Payment savedPayment = underTest.save(payment);
        //Then
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getCustomer()).isEqualTo(customer);
        assertThat(savedPayment.getAmount()).isEqualTo(payment.getAmount());
        assertThat(savedPayment.getCurrency()).isEqualTo(payment.getCurrency());
        assertThat(savedPayment.getDescription()).isEqualTo(payment.getDescription());
        assertThat(savedPayment.getPaidAt()).isEqualTo(payment.getPaidAt());
        assertThat(savedPayment.getSource()).isEqualTo(payment.getSource());
    }
}