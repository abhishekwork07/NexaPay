package com.abhishek.nexapay.payment.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
import com.abhishek.nexapay.common.entity.Money;
import com.abhishek.nexapay.common.enums.PaymentMethod;
import com.abhishek.nexapay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "payment",
        indexes = {
                @Index(name = "idx_payment_order_id", columnList = "order_id"),
                @Index(name = "idx_payment_merchant_id", columnList = "merchant_id"),
        })
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRecord orderRecord;

    // no FK - cross-service boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Column(length = 50)
    private String idempotencyKey;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaymentMethod paymentMethod;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "method_details", columnDefinition = "jsonb")
    private Map<String, Object> methodDetails;

    @Column(nullable = false, length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 255)
    private String errorDescription;

    private LocalDateTime authorizedAt;

    private LocalDateTime capturedAt;

    private LocalDateTime settledAt;

    private LocalDateTime refundedAt;


}
