package com.abhishek.nexapay.merchant.service.impl;

import com.abhishek.nexapay.common.constant.ErrorCode;
import com.abhishek.nexapay.common.enums.MerchantStatus;
import com.abhishek.nexapay.common.enums.UserRole;
import com.abhishek.nexapay.common.exception.DuplicateResourceException;
import com.abhishek.nexapay.merchant.dto.request.MerchantSignUpRequest;
import com.abhishek.nexapay.merchant.dto.response.MerchantResponse;
import com.abhishek.nexapay.merchant.entity.AppUser;
import com.abhishek.nexapay.merchant.entity.Merchant;
import com.abhishek.nexapay.merchant.repo.AppUserRepository;
import com.abhishek.nexapay.merchant.repo.MerchantRepository;
import com.abhishek.nexapay.merchant.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {

    private final MerchantRepository merchantRepository;

    private final AppUserRepository appUserRepository;


    @Override
    @Transactional
    public MerchantResponse merchantSignUp(MerchantSignUpRequest request) {

        if(merchantRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Merchant with email already exists!: " + request.email(),
                    ErrorCode.DUPLICATE_MERCHANT_EMAIL);
        }

        Merchant newMerchant = Merchant.builder()
                .businessName(request.businessName())
                .businessType(request.businessType())
                .name(request.name())
                .email(request.email())
                .status(MerchantStatus.PENDING_KYC)
                .build();

        Merchant savedMerchant = merchantRepository.save(newMerchant);

        AppUser newAppUser = AppUser.builder()
                .merchantId(savedMerchant)
                .email(request.email())
                .role(UserRole.OWNER)
                .passwordHash(request.password()) // TODO: encrypt using Bcrypt
                .build();

        appUserRepository.save(newAppUser);

        return new MerchantResponse(savedMerchant.getId(), savedMerchant.getName(), savedMerchant.getEmail(),
                savedMerchant.getBusinessName(), savedMerchant.getBusinessType(), savedMerchant.getStatus());
    }
}
