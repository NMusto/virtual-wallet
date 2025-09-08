package com.virtual_wallet.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank String email,
                          @NotBlank String password) {}
