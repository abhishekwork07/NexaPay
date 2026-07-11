package com.abhishek.nexapay.merchant.service.impl;

import com.abhishek.nexapay.common.constant.CommonConstants;
import com.abhishek.nexapay.common.exception.ResourceNotFoundException;
import com.abhishek.nexapay.common.util.RandomizerUtil;
import com.abhishek.nexapay.merchant.dto.request.CreateApiKeyRequest;
import com.abhishek.nexapay.merchant.dto.response.ApiKeyCreateResponse;
import com.abhishek.nexapay.merchant.dto.response.ApiKeyResponse;
import com.abhishek.nexapay.merchant.entity.ApiKey;
import com.abhishek.nexapay.merchant.entity.Merchant;
import com.abhishek.nexapay.merchant.mapper.ApiKeyMapper;
import com.abhishek.nexapay.merchant.repo.ApiKeyRepository;
import com.abhishek.nexapay.merchant.repo.MerchantRepository;
import com.abhishek.nexapay.merchant.service.IApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyService implements IApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    private final MerchantRepository merchantRepository;

    private final ApiKeyMapper apiKeyMapper;

    @Override
    public ApiKeyCreateResponse createApiKey(UUID merchantId, CreateApiKeyRequest request) {

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = CommonConstants.NEXAPAY_API_KEY_SUFFIX
                + request.environment().name().toUpperCase()
                + RandomizerUtil.randomBase64(24);

        String rawSecret = RandomizerUtil.randomBase64(40);

        ApiKey newApiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret)
                .enabled(true)
                .environment(request.environment())
                .build();

        ApiKey savedApiKey = apiKeyRepository.save(newApiKey);

        return apiKeyMapper.toCreateResponse(savedApiKey);
    }

    @Override
    public List<ApiKeyResponse> listApiKeys(UUID merchantId) {
        List<ApiKey> apiKeys = apiKeyRepository.findByMerchant_Id(merchantId);
        return apiKeyMapper.toListApiKeyResponse(apiKeys);
    }

    @Override
    @Transactional
    public void revokeApiKey(UUID merchantId, UUID keyId) {
        ApiKey key = validateAndGetApiKey(merchantId, keyId);
        key.setEnabled(false);
    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotateApiKey(UUID merchantId, UUID keyId) {
        ApiKey apiKey = validateAndGetApiKey(merchantId, keyId);

        if(!apiKey.isEnabled()) throw new RuntimeException("Can't rotate a disabled key!");

        String newRawSecret = RandomizerUtil.randomBase64(40);
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));

        return apiKeyMapper.toCreateResponse(apiKey);
    }

    private ApiKey validateAndGetApiKey(UUID merchantId, UUID keyId) {
        return apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));
    }
}
