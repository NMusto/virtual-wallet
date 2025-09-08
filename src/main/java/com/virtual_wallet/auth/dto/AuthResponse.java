package com.virtual_wallet.auth.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponse(String email,
                           String message,
                           String jwt,
                           boolean status) {}
