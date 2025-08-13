package com.virtual_wallet.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthResponse(@NotBlank String email,
                           @NotBlank String password) {}
