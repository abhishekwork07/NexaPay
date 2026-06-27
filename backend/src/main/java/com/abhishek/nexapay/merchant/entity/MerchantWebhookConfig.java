package com.abhishek.nexapay.merchant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "merchant_webhook_config")
public class MerchantWebhookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchantId;

    @Column(nullable = false, length = 500)
    private String targetUrl;

    @Column(nullable = false, length = 500)
    private String eventTypes;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false, length = 255)
    private String webhookSecretHash;
}
