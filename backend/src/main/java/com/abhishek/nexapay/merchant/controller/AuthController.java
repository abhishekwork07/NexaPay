package com.abhishek.nexapay.merchant.controller;

import com.abhishek.nexapay.merchant.dto.response.MerchantResponse;
import com.abhishek.nexapay.merchant.dto.request.MerchantSignUpRequest;
import com.abhishek.nexapay.merchant.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;


    @PostMapping("merchant/signup")
    public ResponseEntity<MerchantResponse> merchantSignUp (@RequestBody @Valid MerchantSignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authService.merchantSignUp(request)
        );
    }
}
