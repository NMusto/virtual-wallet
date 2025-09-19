package com.virtual_wallet.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Virtual Wallet API",
                version = "1.0",
                description = "API to manage transactions, users, and balances of a virtual wallet."
        )
)
public class OpenApiConfig {
}
