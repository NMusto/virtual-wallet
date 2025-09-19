package com.virtual_wallet.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request containing user credentials")
public record AuthRequest(
        @NotBlank
        @Schema(description = "User email", example = "user@example.com")
        String email,

        @NotBlank
        @Schema(description = "User password", example = "P@ssw0rd!")
        String password
) {}
