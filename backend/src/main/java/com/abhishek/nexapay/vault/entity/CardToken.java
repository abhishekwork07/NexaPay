package com.abhishek.nexapay.vault.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
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
@Table(name = "vault_card",
        indexes = {
                @Index(name = "idx_vault_card_id_merchant_id", columnList = "id, merchant_id"),
                @Index(name = "idx_vault_card_merchant_id", columnList = "merchant_id"),
                @Index(name = "idx_vault_card_customer_id", columnList = "customer_id"),
        })
public class CardToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vault_card_id", nullable = false)
    private VaultCard vaultCard;

    // no FK - cross-service boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    // no FK - cross-service boundary
    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    private LocalDateTime revokedAt;


}
