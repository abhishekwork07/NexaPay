package com.abhishek.nexapay.merchant.mapper;

import com.abhishek.nexapay.merchant.dto.response.MerchantResponse;
import com.abhishek.nexapay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    @Mapping(target = "status", source = "status")
    MerchantResponse toResponse(Merchant merchant);
}
