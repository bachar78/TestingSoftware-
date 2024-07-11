package com.bachar.testing.payment;


public record PaymentRequest(
         Double amount,
         Currency currency,
         String source,
         String description
) {
}
