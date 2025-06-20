package ru.faktura.task.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for creating new user")
public record CreateUserRequest(

        @Schema(
                description = "User's first name",
                example = "John",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String firstName,

        @Schema(
                description = "User's last name",
                example = "Doe",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String lastName,

        @Schema(
                description = "User's email address",
                example = "john.doe@example.com",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "email"
        )
        String email,

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