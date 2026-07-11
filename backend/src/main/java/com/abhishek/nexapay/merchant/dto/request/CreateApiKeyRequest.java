package com.abhishek.nexapay.merchant.dto.request;

import com.abhishek.nexapay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
