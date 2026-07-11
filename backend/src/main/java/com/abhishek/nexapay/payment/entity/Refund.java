package com.abhishek.nexapay.payment.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
import com.abhishek.nexapay.common.entity.Money;
import com.abhishek.nexapay.common.enums.RefundStatus;
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
@Table(name = "refund",
        indexes = {
                @Index(name = "idx_refund_merchant_id", columnList = "merchant_id"),
                @Index(name = "idx_refund_payment_id", columnList = "payment_id"),
        })
public class Refund extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    // no FK - cross-service boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private RefundStatus refundStatus = RefundStatus.PENDING;

    @Column(nullable = false, length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 255)
    private String errorDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> notes;

    private LocalDateTime processedAt;
}
