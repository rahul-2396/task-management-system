package com.gokapture.taskmanagement.integrationtests;

import com.gokapture.taskmanagement.enums.Role;
import com.gokapture.taskmanagement.model.User;
import com.gokapture.taskmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegisterAndLoginUser() throws Exception {
        String username = "integrationUser";
        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);

        // Register User
        mockMvc.perform(post("/api/register")
                        .param("username", username)
                        .param("password", password)
                        .param("role", "ROLE_USER"))
                .andExpect(status().isOk());

        User user = userRepository.findByUsername(username).stream().findFirst().orElse(null);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(encodedPassword, user.getPassword());

        // Login User
        mockMvc.perform(post("/api/login")
                        .param("username", username)
                        .param("password", password))
                .andExpect(status().isOk());
    }
}