package com.bachar.testing.customer;

import lombok.Builder;

@Builder
public record CustomerRegistrationRequest(String name, String phoneNumber) {
}
