package com.virtual_wallet.auth.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
@Schema(description = "Response returned after login")
public record AuthResponse(

        @Schema(description = "User email", example = "user@example.com")
        String email,

        @Schema(description = "Additional message for the client", example = "Login successful")
        String message,

        @Schema(description = "JWT token to be used in subsequent requests", example = "eyJhbGciOiJIUzI1NiIsInR...")
        String jwt,

        @Schema(description = "Authentication status", example = "true")
        boolean status) {}
