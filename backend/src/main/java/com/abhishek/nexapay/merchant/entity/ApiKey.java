package com.abhishek.nexapay.merchant.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
import com.abhishek.nexapay.common.enums.Environment;
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
@Table(name = "api_key",
        indexes = {
            @Index(name = "idx_api_key_merchant_env", columnList = "merchant_id, environment, enabled"),
        })
public class ApiKey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false, unique = true, length = 200)
    private String keyId;

    @Column(nullable = false, length = 200)
    private String keySecretHash;

    @Column(length = 200)
    private String previousKeySecretHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Environment environment;

    @Column(nullable = false)
    private boolean enabled = true;

    private LocalDateTime lastUsedAt;

    private LocalDateTime rotatedAt;

    private LocalDateTime gracePeriodExpiresAt;
}
