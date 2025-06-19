package ru.faktura.task.service;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.faktura.task.model.CreateUserRequest;
import ru.faktura.task.model.LoginPassword;
import ru.faktura.task.model.UserResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String serverURL;

    @Value("${keycloak.resource}")
    private String serviceClientID;

    @Value("${keycloak.credentials.secret}")
    private String serviceClientSecret;

    private static Keycloak keycloak;
    private static UsersResource usersResource;
    private static RealmResource realmResource;


    @PostConstruct
    public Keycloak initKeycloak() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverURL)
                    .clientId(serviceClientID)
                    .clientSecret(serviceClientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();
            realmResource = keycloak.realm(realm);
            usersResource = realmResource.users();
        }
        return keycloak;
    }

    public UserResponse getUser(LoginPassword userRequest) {
        String name = userRequest.getLogin();
        boolean userExist = usersResource.searchByAttributes("username:" + name)
            .stream()
            .anyMatch(user -> name.equals(user.getUsername()));
        UserResponse userResponse = null;
        if (userExist) {
            String accessToken;
            String refreshToken;
            String idToken;
            try (Keycloak keycloakUser = KeycloakBuilder.builder()
                    .serverUrl(serverURL)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(serviceClientID)
                    .clientSecret(serviceClientSecret)
                    .scope("openid")
                    .username(name)
                    .password(userRequest.getPassword())
                    .build()) {
                TokenManager tm = keycloakUser.tokenManager();
                accessToken = tm.getAccessTokenString();
                refreshToken = tm.refreshToken().getRefreshToken();
                idToken = tm.getAccessToken().getIdToken();
            }
            userResponse = new UserResponse(accessToken, idToken, refreshToken);
        }
        return userResponse;
    }

    public String createUser(CreateUserRequest user) {
        String login = user.getLogin();
        String userId = null;
        boolean notExist = usersResource.searchByAttributes("username:" + login)
            .stream()
            .noneMatch(el -> login.equals(el.getUsername()));
        if (notExist) {
            String password = user.getPassword();
            UserRepresentation kcUser = new UserRepresentation();
            kcUser.setCredentials(Collections.singletonList(createPasswordCredentials(password)));
            kcUser.setUsername(login);
            kcUser.setEmail(user.getEmail());
            kcUser.setFirstName(user.getFirstName());
            kcUser.setLastName(user.getLastName());
            kcUser.setEnabled(true);
            kcUser.setEmailVerified(true);
            try (Response response = usersResource.create(kcUser)) {
                userId = ((String) response.getMetadata().get("Location").get(0)).split("users/")[1];
                addRoles(userId,"user");
            } catch (Exception e) {
                log.info("Пользователь не создан: {}", login);
            }
        }
        log.info("Пользователь создан: {} - {}", userId, login);
        return userId;
    }

    private void addRoles(String userId, String role) {
        List<RoleRepresentation> kcRoles = new ArrayList<>();
        realmResource.roles().list()
            .stream()
            .filter(el -> role.equals(el.getName()))
            .findFirst()
            .ifPresentOrElse(
                el -> {
                    kcRoles.add(el);
                    usersResource.get(userId).roles().realmLevel().add(kcRoles);
                   },
                () -> {}
            );
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
