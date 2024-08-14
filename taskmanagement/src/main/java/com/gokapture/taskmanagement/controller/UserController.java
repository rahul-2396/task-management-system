package com.gokapture.taskmanagement.controller;

import com.gokapture.taskmanagement.enums.Role;
import com.gokapture.taskmanagement.model.User;
import com.gokapture.taskmanagement.service.UserService;
import com.gokapture.taskmanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    private User registerUser(@RequestParam String username, @RequestParam String password, @RequestParam Set<Role> roles)
    {
        return userService.registerUser(username, password, roles);
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password)
    {
        UserDetails userDetails = userService.loadUserByUsername(username);
        if(userDetails != null && passwordEncoder.matches(password, userDetails.getPassword()))
        {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid Credentials..");
    }
}

