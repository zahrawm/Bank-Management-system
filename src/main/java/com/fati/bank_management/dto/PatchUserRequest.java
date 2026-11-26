package com.fati.bank_management.dto;

import jakarta.validation.constraints.Email;

public record PatchUserRequest(

        String fullName,

        @Email(message = "Email must be valid")
        String email
) {}