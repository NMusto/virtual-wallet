package com.virtual_wallet.auth.service;

import com.virtual_wallet.auth.dto.AuthRequest;
import com.virtual_wallet.auth.dto.AuthResponse;

public interface AuthService {
    AuthResponse loginUser(AuthRequest authRequest);
}
