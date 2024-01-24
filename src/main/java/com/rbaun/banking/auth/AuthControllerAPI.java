package com.rbaun.banking.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API for authentication operations
 */
@RequestMapping("/auth")
public interface AuthControllerAPI {

    /**
     * Request to login
     * @param authRequest the credentials to login with
     * @return @{@link JwtResponse} with the JWT token
     */
    @PostMapping("/login")
    JwtResponse login(@RequestBody AuthRequest authRequest);

    /**
     * Request to register a new user
     * @param authRequest the credentials to register with
     * @return @{@link JwtResponse} with the JWT token
     */
    @PostMapping("/register")
    JwtResponse register(@RequestBody AuthRequest authRequest);

}
