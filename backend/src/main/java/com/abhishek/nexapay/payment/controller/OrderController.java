package com.abhishek.nexapay.payment.controller;

import com.abhishek.nexapay.payment.dto.request.CreateOrderRequest;
import com.abhishek.nexapay.payment.dto.response.OrderResponse;
import com.abhishek.nexapay.payment.dto.response.PaymentResponse;
import com.abhishek.nexapay.payment.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;
    UUID merchantId = UUID.fromString("d0f3ac57-8bcb-431b-86e5-5290ea534fc3"); // TODO: replace it with merchantContext

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(merchantId, request));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderRecord(@PathVariable UUID orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrderById(merchantId, orderId));
    }

    @GetMapping("/list-payments")
    public ResponseEntity<List<PaymentResponse>> getPaymentListByOrderId(@PathVariable UUID orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.listPayments(merchantId, orderId));
    }

    @PostMapping("/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable UUID orderId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(orderService.cancelOrderById(merchantId, orderId));
    }


}
