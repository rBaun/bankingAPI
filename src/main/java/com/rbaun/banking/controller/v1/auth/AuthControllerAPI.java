package com.rbaun.banking.controller.v1.auth;

import com.rbaun.banking.controller.v1.auth.request.AuthRequest;
import com.rbaun.banking.controller.v1.auth.response.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API for authentication operations
 */
@Tag(name = "Operations for user sign in and registration")
@RequestMapping("/auth")
public interface AuthControllerAPI {

    /**
     * Request to login
     * @param authRequest the credentials to login with
     * @return @{@link JwtResponse} with the JWT token
     */
    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Login with username and password to get a JWT token",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The credentials to login with",
                    required = true
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "JWT token",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = JwtResponse.class)
                            )
                    )
            }
    )
    JwtResponse login(@RequestBody AuthRequest authRequest);

    /**
     * Request to register a new user
     * @param authRequest the credentials to register with
     * @return @{@link JwtResponse} with the JWT token
     */
    @PostMapping("/register")
    @Operation(
            summary = "Register",
            description = "Register a new user with username and password to get a JWT token",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The credentials to register with",
                    required = true
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "JWT token",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = JwtResponse.class)
                            )
                    )
            }
    )
    JwtResponse register(@RequestBody AuthRequest authRequest);

}
