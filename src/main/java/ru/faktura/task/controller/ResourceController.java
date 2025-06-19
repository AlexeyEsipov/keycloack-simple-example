package ru.faktura.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.faktura.task.service.ResourceService;

@RestController
@RequestMapping("/quard")
@RequiredArgsConstructor
@Tag(name = "Resource Controller", description = "Контроллер для работы с защищенными ресурсами")
@SecurityRequirement(name = "bearerAuth")
public class ResourceController {

    private final ResourceService resourceService;

    @Operation(
            summary = "Получить приветствие",
            description = "Возвращает приветствие для аутентифицированного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешное получение приветствия",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователь не аутентифицирован"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещен"
                    )
            }
    )
    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(
            @Parameter(
                    description = "JWT токен пользователя",
                    required = true,
                    hidden = true
            )
            @AuthenticationPrincipal Jwt jwt
    ) {
        String name = resourceService.getName(jwt);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
}