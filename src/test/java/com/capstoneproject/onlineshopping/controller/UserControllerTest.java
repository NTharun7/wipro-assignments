package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = UserController.class,
        excludeFilters = {
            @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = com.capstoneproject.onlineshopping.security.AuthTokenFilter.class),
            @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = com.capstoneproject.onlineshopping.security.JwtUtils.class)
        }
)
@AutoConfigureMockMvc(addFilters = false)  // ✅ disables Spring Security
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        Mockito.when(userRepository.findAll())
                .thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test User"));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Alice");

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testUpdateUser() throws Exception {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Old Name");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("New Name");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Name\", \"email\": \"new@example.com\", \"address\": \"New Address\", \"phone\": \"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }
}
