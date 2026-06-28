package com.abhishek.nexapay.merchant.service;

import com.abhishek.nexapay.merchant.dto.request.MerchantSignUpRequest;
import com.abhishek.nexapay.merchant.dto.response.MerchantResponse;

public interface IAuthService {

    MerchantResponse merchantSignUp(MerchantSignUpRequest request);
}
