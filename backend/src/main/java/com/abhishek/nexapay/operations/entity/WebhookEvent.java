package com.abhishek.nexapay.operations.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
import com.abhishek.nexapay.common.enums.WebhookEventStatus;
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
@Table(name = "webhook_event")
public class WebhookEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // no FK - cross-service boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Column(nullable = false, length = 100)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "jsonb")
    private Map<String, Object> payload;

    @Column(nullable = false, length = 255)
    private String targetUrl;

    @Column(nullable = false, length = 100)
    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private WebhookEventStatus status;

    @Column(nullable = false)
    private Integer attempts = 0;

    @Column(nullable = false)
    private Integer lastResponseCode;

    @Column(nullable = false, length = 1000)
    private String lastResponseBody;

    @Column(nullable = false)
    private LocalDateTime lastDeliveredAt;

    @Column(nullable = false)
    private LocalDateTime nextRetryAt;

    private LocalDateTime lastRetryAt;
}
