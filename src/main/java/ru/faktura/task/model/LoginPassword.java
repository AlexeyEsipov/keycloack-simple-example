package ru.faktura.task.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for user authentication request")
public class LoginPassword {

    @Schema(
            description = "User's login (username)",
            example = "johndoe",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 4,
            maxLength = 20
    )
    private String login;

    @Schema(
            description = "User's password",
            example = "mySecurePassword123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 8,
            maxLength = 30
    )
    private String password;
}