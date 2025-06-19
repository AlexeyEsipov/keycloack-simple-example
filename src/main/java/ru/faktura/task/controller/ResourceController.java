package ru.faktura.task.controller;

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
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(@AuthenticationPrincipal Jwt jwt) {
        String name = resourceService.getName(jwt);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
}
