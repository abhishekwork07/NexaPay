package com.abhishek.nexapay.common.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class Money {

    private int amountUnits;
    private String currency;

    public Money add(Money other) {
        validateCurrency(other);
        return new Money(this.amountUnits + other.amountUnits, this.currency);
    }

    public Money subtract(Money other) {
        validateCurrency(other);
        return new Money(this.amountUnits - other.amountUnits, this.currency);
    }

    public static Money of(int amountUnits, String currency) {
        return new Money(amountUnits, currency);
    }

    public static Money inr(int amountUnits) {
        return new Money(amountUnits, "INR");
    }

    private void validateCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    "Currency mismatch. Expected " + this.currency +
                            " but found " + other.currency
            );
        }
    }


}
