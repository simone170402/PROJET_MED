package org.example.controller;

import fr.univ.orleans.info.ihm.controller.AuthController;
import fr.univ.orleans.info.ihm.model.User;
import fr.univ.orleans.info.ihm.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private AuthController authController;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setEmail("test@example.com");
        testUser.setFirstName("Test");
        testUser.setLastName("User");
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void register_WithValidUser_ShouldReturnCreated() {
        // Arrange
        when(userService.registerUser(any(User.class))).thenReturn(testUser);

        // Act
        ResponseEntity<Map<String, Object>> response = authController.register(testUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testUser.getUsername(), response.getBody().get("username"));
        assertEquals("User registered successfully", response.getBody().get("message"));
    }

    @Test
    void register_WhenUserServiceThrowsException_ShouldReturnBadRequest() {
        // Arrange
        when(userService.registerUser(any(User.class)))
            .thenThrow(new RuntimeException("Username already exists"));

        // Act
        ResponseEntity<Map<String, Object>> response = authController.register(testUser);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Username already exists", response.getBody().get("error"));
    }

    @Test
    void login_WithValidCredentials_ShouldReturnOk() {
        // Arrange
        Map<String, String> credentials = Map.of(
            "username", "testuser",
            "password", "password123"
        );
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Act
        ResponseEntity<Map<String, Object>> response = authController.login(credentials);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Login successful", response.getBody().get("message"));
        assertEquals("testuser", response.getBody().get("username"));
    }

    @Test
    void login_WhenAuthenticationFails_ShouldReturnUnauthorized() {
        // Arrange
        Map<String, String> credentials = Map.of(
            "username", "testuser",
            "password", "wrongpassword"
        );
        when(securityContext.getAuthentication()).thenThrow(new RuntimeException("Authentication failed"));

        // Act
        ResponseEntity<Map<String, Object>> response = authController.login(credentials);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid username or password", response.getBody().get("error"));
    }

    @Test
    void logout_ShouldClearContextAndReturnOk() {
        // Act
        ResponseEntity<Map<String, Object>> response = authController.logout();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Logged out successfully", response.getBody().get("message"));
    }

    @Test
    void checkAuthStatus_WhenAuthenticated_ShouldReturnTrue() {
        // Arrange
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        when(authentication.isAuthenticated()).thenReturn(true);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Act
        ResponseEntity<Map<String, Object>> response = authController.checkAuthStatus();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue((Boolean) response.getBody().get("authenticated"));
        assertEquals("testuser", response.getBody().get("username"));
    }

    @Test
    void checkAuthStatus_WhenNotAuthenticated_ShouldReturnFalse() {
        // Arrange
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Act
        ResponseEntity<Map<String, Object>> response = authController.checkAuthStatus();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse((Boolean) response.getBody().get("authenticated"));
    }
}
