package com.bachar.testing.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    boolean existsCustomerByPhoneNumber(String phone);
//    Optional<Customer> findCustomerByPhoneNumber(String phone);

    @Query(value = "select id, name, phone_number " +
    "from customer where phone_number = :phone_number",
    nativeQuery = true)
    Optional<Customer> findCustomerByPhoneNumber(@Param("phone_number") String phone_number);

}
