package com.abhishek.nexapay.payment.service;

import com.abhishek.nexapay.payment.dto.request.CreateOrderRequest;
import com.abhishek.nexapay.payment.dto.response.OrderResponse;
import com.abhishek.nexapay.payment.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface IOrderService {

    OrderResponse createOrder(UUID merchantId, CreateOrderRequest request);

    OrderResponse getOrderById(UUID merchantId, UUID orderId);

    OrderResponse cancelOrderById(UUID merchantId, UUID orderId);

    List<PaymentResponse> listPayments(UUID merchantId, UUID orderId);
}
