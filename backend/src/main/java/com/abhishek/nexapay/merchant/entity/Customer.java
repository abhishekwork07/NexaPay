package com.abhishek.nexapay.merchant.entity;

import com.abhishek.nexapay.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer",
        indexes = {
                @Index(name = "idx_customer_merchant_id", columnList = "merchant_id"),
                @Index(name = "idx_customer_email", columnList = "email"),
        })
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchantId;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(length = 15)
    private String contactNumber;

}
