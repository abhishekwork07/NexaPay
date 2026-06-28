package com.abhishek.nexapay.merchant.repo;

import com.abhishek.nexapay.merchant.entity.Merchant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    boolean existsByEmail(@Email(message = "Please provide a valid email address")
                          @NotNull(message = "Email should be provided for the signUp!")
                          String email);
}
