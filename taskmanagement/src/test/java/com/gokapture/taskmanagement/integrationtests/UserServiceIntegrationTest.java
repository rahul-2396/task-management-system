package com.gokapture.taskmanagement.integrationtests;

import com.gokapture.taskmanagement.enums.Role;
import com.gokapture.taskmanagement.model.User;
import com.gokapture.taskmanagement.repository.UserRepository;
import com.gokapture.taskmanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegisterUser() {
        String username = "serviceUser";
        String password = "password";
        Set<Role> role = Set.of(Role.ROLE_USER);

        User user = userService.registerUser(username, password, role);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertTrue(passwordEncoder.matches(password, user.getPassword()));
    }

    @Test
    void testFindByUsername() {
        String username = "serviceUser";
        String password = "password";
        Set<Role> role = Set.of(Role.ROLE_USER);

        User user = userService.registerUser(username, password, role);

        User foundUser = userService.findByUsername(username);
        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());
    }

    @Test
    void testFindByUsername_NotFound() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.findByUsername("unknownUser");
        });
    }
}
