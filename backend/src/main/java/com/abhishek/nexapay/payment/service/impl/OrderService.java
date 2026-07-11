package com.abhishek.nexapay.payment.service.impl;

import com.abhishek.nexapay.common.constant.ErrorCode;
import com.abhishek.nexapay.common.enums.OrderStatus;
import com.abhishek.nexapay.common.exception.BusinessRuleViolationException;
import com.abhishek.nexapay.common.exception.DuplicateResourceException;
import com.abhishek.nexapay.common.exception.ResourceNotFoundException;
import com.abhishek.nexapay.payment.dto.request.CreateOrderRequest;
import com.abhishek.nexapay.payment.dto.response.OrderResponse;
import com.abhishek.nexapay.payment.dto.response.PaymentResponse;
import com.abhishek.nexapay.payment.entity.OrderRecord;
import com.abhishek.nexapay.payment.entity.Payment;
import com.abhishek.nexapay.payment.mapper.OrderRecordMapper;
import com.abhishek.nexapay.payment.mapper.PaymentMapper;
import com.abhishek.nexapay.payment.repo.OrderRepository;
import com.abhishek.nexapay.payment.repo.PaymentRepository;
import com.abhishek.nexapay.payment.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderRecordMapper orderRecordMapper;

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Value("${payment.order.default-order-expiry-mins:30}")
    private int defaultOrderExpiryMins;

    @Override
    public OrderResponse createOrder(UUID merchantId, CreateOrderRequest request) {
        validateOrderExistence(merchantId, request);

        OrderRecord newOrder = OrderRecord.builder()
                .idempotencyKey(request.receipt())
                .amount(request.amount())
                .notes(request.notes())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .expiresAt(request.expiresAt() != null ? request.expiresAt() :
                        LocalDateTime.now().plusMinutes(defaultOrderExpiryMins))
                .attempts(1)
                .build();

        OrderRecord savedOrder = orderRepository.save(newOrder);

        // TODO: publish Kafka event about order creation

        return orderRecordMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(UUID merchantId, UUID orderId) {
        OrderRecord orderRecord = validateAndGetOrder(merchantId, orderId);
        return orderRecordMapper.toOrderResponse(orderRecord);
    }

    @Override
    @Transactional
    public OrderResponse cancelOrderById(UUID merchantId, UUID orderId) {
        OrderRecord orderRecord = validateAndGetOrder(merchantId, orderId);

        OrderStatus orderStatus = orderRecord.getOrderStatus();
        if(orderStatus == OrderStatus.CANCELLED || orderStatus == OrderStatus.PAID) {
            throw new BusinessRuleViolationException(ErrorCode.ORDER_CANNOT_CANCEL,
                    "Can't cancel order with status: " + orderStatus.name());
        }

        orderRecord.setOrderStatus(OrderStatus.CANCELLED);
        return orderRecordMapper.toOrderResponse(orderRepository.save(orderRecord));
    }

    @Override
    public List<PaymentResponse> listPayments(UUID merchantId, UUID orderId) {
        OrderRecord orderRecord = validateAndGetOrder(merchantId, orderId);
        List<Payment> paymentList = paymentRepository.findByOrderRecord_Id(orderRecord.getId());

        return paymentMapper.toPaymentResponseList(paymentList);
    }

    private void validateOrderExistence(UUID merchantId, CreateOrderRequest request) {
        if(request.receipt() != null &&
                orderRepository.existsByMerchantIdAndIdempotencyKey(merchantId, request.receipt())) {
            throw new DuplicateResourceException(ErrorCode.DUPLICATE_ORDER_RECEIPT,
                    "Order with receipt already exists!");
        }
    }

    private OrderRecord validateAndGetOrder(UUID merchantId, UUID orderId) {
        return orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
    }
}
