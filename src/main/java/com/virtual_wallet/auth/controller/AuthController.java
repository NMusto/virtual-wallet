package com.virtual_wallet.auth.controller;

import com.virtual_wallet.auth.service.AuthServiceImpl;
import com.virtual_wallet.auth.dto.AuthRequest;
import com.virtual_wallet.auth.dto.AuthResponse;
import com.virtual_wallet.common.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Operations related to user authentication")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;


    @Operation(
            summary = "Authenticate user and generate JWT",
            description = "Validates the provided credentials and returns a JWT token if successful."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or validation error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User credentials required for authentication")
            @RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authServiceImpl.loginUser(authRequest));
    }
}
