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
@Table(name = "vault_card")
public class VaultCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private byte[] encryptedPan;

    @Column(nullable = false)
    private byte[] encryptedDek;

    @Column(nullable = false, length = 4)
    private String lastFourDigits;

    @Column(nullable = false, length = 20)
    private String brand;

    @Column(nullable = false, length = 6)
    private String bin;

    @Column(nullable = false, length = 2)
    private Integer expiryMonth;

    @Column(nullable = false, length = 4)
    private Integer expiryYear;

    @Column(nullable = false, length = 50)
    private String cardHolderName;

    private LocalDateTime deletedAt;

}
