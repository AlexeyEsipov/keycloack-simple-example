package ru.faktura.task.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    public String getName(Jwt jwt) {
        return jwt.getClaimAsString("name");
    }
}
