package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("plainPassword");
    }

    // ✅ Test successful user registration
    @Test
    void testRegisterUser_Success() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertNotEquals("plainPassword", savedUser.getPassword()); // password should be encoded
        assertTrue(new BCryptPasswordEncoder().matches("plainPassword", savedUser.getPassword()));
        verify(userRepository, times(1)).save(any(User.class));
    }

    // ✅ Test registration failure (duplicate email)
    @Test
    void testRegisterUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.registerUser(user));

        assertEquals("Email already registered!", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    // ✅ Test finding user by email
    @Test
    void testFindByEmail() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }
}
