package com.abhishek.nexapay.payment.dto.response;

import com.abhishek.nexapay.common.entity.Money;
import com.abhishek.nexapay.common.enums.PaymentMethod;
import com.abhishek.nexapay.common.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(
        UUID id,
        UUID orderId,
        UUID merchantId,
        Money amount,
        PaymentStatus status,
        PaymentMethod method,
        Map<String, Object> methodDetails,
        String errorCode,
        String errorDescription,
        Money refundAmount,
        LocalDateTime capturedAt,
        LocalDateTime createdAt
) {
}
