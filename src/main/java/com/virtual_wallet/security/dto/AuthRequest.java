package com.virtual_wallet.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank String email,
                          @NotBlank String password) {}
