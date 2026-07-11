package com.abhishek.nexapay.merchant.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
import com.abhishek.nexapay.common.enums.BusinessType;
import com.abhishek.nexapay.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "merchant",
        indexes = {
                @Index(name = "idx_merchant_status", columnList = "status"),
        })
public class Merchant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true, length = 15)
    private String contactNumber;

    @Column(length = 200)
    private String businessName;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private MerchantStatus status = MerchantStatus.PENDING_KYC;

    @Column(length = 200)
    private String websiteUrl;

    @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 100)
    private String settlementAccountHolderName;

    @Column(length = 20)
    private String settlementBankAccount;

    @Column(length = 20)
    private String settlementBankIfsc;

}
