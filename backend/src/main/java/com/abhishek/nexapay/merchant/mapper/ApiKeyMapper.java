package com.abhishek.nexapay.merchant.mapper;

import com.abhishek.nexapay.merchant.dto.response.ApiKeyCreateResponse;
import com.abhishek.nexapay.merchant.dto.response.ApiKeyResponse;
import com.abhishek.nexapay.merchant.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

    @Mapping(target = "keySecret", source = "keySecretHash")
    ApiKeyCreateResponse toCreateResponse(ApiKey apiKey);

    List<ApiKeyResponse> toListApiKeyResponse(List<ApiKey> apiKeys);
}
