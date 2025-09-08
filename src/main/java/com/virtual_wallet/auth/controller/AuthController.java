package com.virtual_wallet.auth.controller;

import com.virtual_wallet.security.dto.AuthRequest;
import com.virtual_wallet.security.dto.AuthResponse;
import com.virtual_wallet.user.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsService.loginUser(authRequest));
    }
}
