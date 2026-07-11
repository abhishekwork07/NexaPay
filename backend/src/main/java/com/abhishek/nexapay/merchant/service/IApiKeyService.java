package com.abhishek.nexapay.merchant.service;

import com.abhishek.nexapay.merchant.dto.request.CreateApiKeyRequest;
import com.abhishek.nexapay.merchant.dto.response.ApiKeyCreateResponse;
import com.abhishek.nexapay.merchant.dto.response.ApiKeyResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface IApiKeyService {

    ApiKeyCreateResponse createApiKey(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listApiKeys(UUID merchantId);

    void revokeApiKey(UUID merchantId, UUID keyId);

    ApiKeyCreateResponse rotateApiKey(UUID merchantId, UUID keyId);
}
