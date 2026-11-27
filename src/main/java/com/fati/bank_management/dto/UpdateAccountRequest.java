package com.fati.bank_management.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateAccountRequest(

        @NotBlank(message = "Account name is required")
        String accountName
) {}