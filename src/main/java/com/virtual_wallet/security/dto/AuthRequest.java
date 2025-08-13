package com.virtual_wallet.security.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthRequest(String email,
                          String message,
                          String jwt,
                          boolean status) {}
