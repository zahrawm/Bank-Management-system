package com.fati.bank_management.dto;

import com.fati.bank_management.entity.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequest(

        @NotNull(message = "User ID is required")
        Long userId,

        @NotBlank(message = "Account name is required")
        String accountName,

        @NotNull(message = "Account type is required")
        AccountType accountType
) {}