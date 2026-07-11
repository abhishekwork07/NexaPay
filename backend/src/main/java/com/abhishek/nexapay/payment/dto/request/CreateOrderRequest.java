package com.abhishek.nexapay.payment.dto.request;

import com.abhishek.nexapay.common.entity.Money;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateOrderRequest(

        @NotNull(message = "Order amount can't be null")
        Money amount,

        @Size(max = 50)
        String receipt, // act as an idempotencyKey

        Map<String, Object> notes,

        LocalDateTime expiresAt
) {
}
