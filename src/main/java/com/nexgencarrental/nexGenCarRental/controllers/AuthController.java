package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AuthService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    //private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public void register(@RequestBody CreateUserRequest request)
    {
        authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if(authentication.isAuthenticated())
        {
            // jwt oluştur.
            Map<String,Object> claims = new HashMap<>();
            claims.put("roles",new String("Admin"));
            return jwtService.generateToken(request.getEmail(), claims);
        }
        throw new RuntimeException("Bilgiler hatalı");
    }
}
