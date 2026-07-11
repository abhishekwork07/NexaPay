package com.abhishek.nexapay.merchant.dto.response;

import com.abhishek.nexapay.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String keyId,
        String keySecret,
        Environment environment
) {
}
