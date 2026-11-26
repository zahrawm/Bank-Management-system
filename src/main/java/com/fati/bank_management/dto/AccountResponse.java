package com.fati.bank_management.dto;

import com.fati.bank_management.entity.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String accountNumber,
        String accountName,
        AccountType accountType,
        BigDecimal balance,
        Long userId,
        LocalDateTime createdAt
) {}