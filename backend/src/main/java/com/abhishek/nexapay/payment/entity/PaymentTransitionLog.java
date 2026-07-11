package com.abhishek.nexapay.payment.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
import com.abhishek.nexapay.common.enums.PaymentActor;
import com.abhishek.nexapay.common.enums.PaymentEvent;
import com.abhishek.nexapay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "payment_transition_log",
        indexes = {
                @Index(name = "idx_payment_transition_log_payment_id", columnList = "payment_id")
        })
public class PaymentTransitionLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaymentStatus toStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaymentEvent eventType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PaymentActor actor;

    @Column(length = 255)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime occurredAt;
}
