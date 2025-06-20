package ru.faktura.task.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for user authentication request")
public record LoginPassword(
        @Schema(
                description = "User's login (username)",
                example = "johndoe",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 4,
                maxLength = 20
        )
        String login,

        @Schema(
                description = "User's password",
                example = "mySecurePassword123",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 8,
                maxLength = 30
        )
        String password
) {}