package com.abhishek.nexapay.payment.mapper;

import com.abhishek.nexapay.payment.dto.response.PaymentResponse;
import com.abhishek.nexapay.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    @Mapping(target = "orderId", source = "orderRecord.id")
    PaymentResponse toPaymentResponse(Payment payment);

    @Mapping(target = "orderId", source = "orderRecord.id")
    List<PaymentResponse> toPaymentResponseList(List<Payment> payments);
}
