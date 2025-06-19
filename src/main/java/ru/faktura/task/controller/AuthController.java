package ru.faktura.task.controller;

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
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> getAuthToken(@RequestBody LoginPassword user) {
        UserResponse userResponse = userService.getUser(user);
        return userResponse == null
                ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<HttpStatus> createUser(@RequestBody CreateUserRequest user) {
        String userId = userService.createUser(user);
        return new ResponseEntity<>(
            userId == null
                ? HttpStatus.CONFLICT
                : HttpStatus.CREATED);
    }
}
