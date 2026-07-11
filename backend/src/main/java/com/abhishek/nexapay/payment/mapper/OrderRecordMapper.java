package com.abhishek.nexapay.payment.mapper;

import com.abhishek.nexapay.payment.dto.response.OrderResponse;
import com.abhishek.nexapay.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderRecordMapper {

    @Mapping(target = "receipt", source = "idempotencyKey")
    @Mapping(target = "status", source = "orderStatus")
    OrderResponse toOrderResponse(OrderRecord record);
}
