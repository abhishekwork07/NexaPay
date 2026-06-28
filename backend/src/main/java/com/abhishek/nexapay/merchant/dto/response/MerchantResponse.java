package com.abhishek.nexapay.merchant.dto.response;

import com.abhishek.nexapay.common.enums.BusinessType;
import com.abhishek.nexapay.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(

        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus status

) {
}
