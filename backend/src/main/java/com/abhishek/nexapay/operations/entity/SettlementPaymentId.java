package com.abhishek.nexapay.operations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SettlementPaymentId implements Serializable {

    // no FK - cross-service boundary
    @Column(name = "settlement_id", nullable = false)
    private UUID settlementId;

    // no FK - cross-service boundary
    @Column(name = "payment_id", nullable = false)
    private UUID paymentId;
}
