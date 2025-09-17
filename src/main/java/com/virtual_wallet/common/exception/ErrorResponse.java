package com.virtual_wallet.common.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;
}