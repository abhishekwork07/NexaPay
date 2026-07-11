package com.abhishek.nexapay.merchant.repo;

import com.abhishek.nexapay.merchant.dto.response.ApiKeyResponse;
import com.abhishek.nexapay.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {

    List<ApiKey> findByMerchant_Id(UUID merchantId);
}
