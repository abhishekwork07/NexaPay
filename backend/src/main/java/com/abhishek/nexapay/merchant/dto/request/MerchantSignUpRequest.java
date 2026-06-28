package com.abhishek.nexapay.merchant.dto.request;

import com.abhishek.nexapay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignUpRequest(

        @NotNull(message = "Name should be provided for the signUp!")
        @Size(max = 50, message = "Name should not be more than 50 characters long")
        String name,

        @Email(message = "Please provide a valid email address")
        @NotNull(message = "Email should be provided for the signUp!")
        String email,

        @NotNull(message = "Password should be provided for the signUp!")
        @Size(max = 8, message = "Password should not be more than 50 characters long")
        String password,

        @Size(max = 100, message = "Business Name should not be more than 100 characters long")
        String businessName,

        BusinessType businessType
) {
}
