package ru.faktura.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.faktura.task.model.CreateUserRequest;
import ru.faktura.task.model.LoginPassword;
import ru.faktura.task.model.UserResponse;
import ru.faktura.task.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API for user authentication and registration")
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Authenticate user", description = "Get authentication token for existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "409", description = "Authentication failed - user not found or invalid credentials",
                    content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> getAuthToken(@RequestBody LoginPassword user) {
        UserResponse userResponse = userService.getUser(user);
        return userResponse == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Register new user", description = "Create a new user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content)
    })
    @PostMapping("/signin")
    public ResponseEntity<HttpStatus> createUser(@RequestBody CreateUserRequest user) {
        String userId = userService.createUser(user);
        return new ResponseEntity<>(
                userId == null
                        ? HttpStatus.CONFLICT
                        : HttpStatus.CREATED);
    }
}
