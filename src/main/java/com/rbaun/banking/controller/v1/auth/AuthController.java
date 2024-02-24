package com.rbaun.banking.controller.v1.auth;

import com.rbaun.banking.controller.v1.auth.request.AuthRequest;
import com.rbaun.banking.controller.v1.auth.response.JwtResponse;
import com.rbaun.banking.auth.JwtUtil;
import com.rbaun.banking.auth.UserRepository;
import com.rbaun.banking.model.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthControllerAPI {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(AuthRequest authRequest) {
        logger.info("Got request to authenticate user: {}", authRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        throwIfAuthenticationFailed(authentication);
        String token = jwtUtil.generateToken(authRequest.username());
        logger.info("Authenticated user {} and generated token: {}", authRequest.username(), token);

        return new JwtResponse(jwtUtil.generateToken(authRequest.username()));
    }

    @Override
    public JwtResponse register(AuthRequest authRequest) {
        logger.info("Got request to register user: {}", authRequest);

        // Check if the username already exists
        UserInfo existingUser = userRepository.findByUsername(authRequest.username());
        if (existingUser != null) {
            logger.debug("Registration failed: Username already exists");
            throw new UsernameNotFoundException("Registration failed: Username already exists");
        }

        // Create a new user
        UserInfo newUser = new UserInfo();
        newUser.setUsername(authRequest.username());
        newUser.setPassword(passwordEncoder.encode(authRequest.password()));

        // Save the new user in the database
        userRepository.save(newUser);

        // Generate a JWT token for the new user
        String token = jwtUtil.generateToken(newUser.getUsername());
        logger.info("Registered user {} and generated token: {}", newUser.getUsername(), token);

        return new JwtResponse(token);
    }

    private void throwIfAuthenticationFailed(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            logger.debug("Authentication failed: Invalid username or password");
            throw new UsernameNotFoundException("Authentication failed: Invalid username or password");
        }
    }
}
