package com.abhishek.nexapay.merchant.controller;

import com.abhishek.nexapay.merchant.dto.request.CreateApiKeyRequest;
import com.abhishek.nexapay.merchant.dto.response.ApiKeyCreateResponse;
import com.abhishek.nexapay.merchant.dto.response.ApiKeyResponse;
import com.abhishek.nexapay.merchant.service.IApiKeyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/merchant/{merchantId}/api-key")
@AllArgsConstructor
public class ApiKeyController {

    private final IApiKeyService apiKeyService;

    @PostMapping("/create")
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId,
                                                       @Valid @RequestBody CreateApiKeyRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                apiKeyService.createApiKey(merchantId, request)
        );
    }

    @GetMapping()
    public ResponseEntity<List<ApiKeyResponse>> listApiKeys(@PathVariable UUID merchantId) {
        return ResponseEntity.ok(apiKeyService.listApiKeys(merchantId));
    }

    @DeleteMapping("/{keyId}/revoke")
    public ResponseEntity<Void> listApiKeys(@PathVariable UUID merchantId, @PathVariable UUID keyId) {
        apiKeyService.revokeApiKey(merchantId, keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotateApiKey(@PathVariable UUID merchantId,
                                                             @PathVariable UUID keyId) {
        return ResponseEntity.ok(apiKeyService.rotateApiKey(merchantId, keyId));
    }
}
