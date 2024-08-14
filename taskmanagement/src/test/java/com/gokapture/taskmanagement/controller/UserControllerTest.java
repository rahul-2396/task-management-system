package com.gokapture.taskmanagement.controller;

import com.gokapture.taskmanagement.enums.Role;
import com.gokapture.taskmanagement.model.User;
import com.gokapture.taskmanagement.service.UserService;
import com.gokapture.taskmanagement.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    public UserControllerTest()
    {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testRegisterUser() throws Exception
    {
        User user = new User(1L, "testuser", "encodedPassword", Set.of(Role.ROLE_USER));

        when(userService.registerUser(any(String.class), any(String.class), any(Set.class))).thenReturn(user);

        mockMvc.perform(post("/api/register")
                        .param("username", "testuser")
                        .param("password", "password")
                        .param("role", "ROLE_USER"))
                .andExpect(status().isOk());
    }

    @Test
    void testLoginUser() throws Exception
    {
        User user = new User(1L, "testuser", "encodedPassword", Set.of(Role.ROLE_USER));

        when(userService.loadUserByUsername("testuser")).thenReturn(user);
        when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);
        when(jwtUtil.generateToken("testuser")).thenReturn("mockedToken");

        mockMvc.perform(post("/api/login")
                        .param("username", "testuser")
                        .param("password", "password"))
                .andExpect(status().isOk());
    }
}

