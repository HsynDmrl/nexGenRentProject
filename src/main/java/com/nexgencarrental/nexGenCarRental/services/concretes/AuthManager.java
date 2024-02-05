package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AuthService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegisterRequest request) {
        try {
            if (userService.existsByEmail(request.getEmail())) {
                throw new EntityExistsException(ErrorConstants.USER_ALREADY_EXISTS + ": " + request.getEmail());
            }

            Role userRole = userService.findRoleById(request.getRoleId())
                    .orElseThrow(() -> new EntityNotFoundException(ErrorConstants.ROLE_NOT_FOUND + " with id: " + request.getRoleId()));

            String encodedPassword = passwordEncoder.encode(request.getPassword());

            User user = User.builder()
                    .email(request.getEmail())
                    .password(encodedPassword)
                    .role(userRole)
                    .build();

            userService.add(user);

        } catch (EntityExistsException | EntityNotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.REGISTRATION_ERROR, ex);
        }
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();

            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());

            String accessToken = jwtService.generateToken(user.getUsername(), claims);
            String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);

            return authResponse;

        } catch (AuthenticationException ex) {
            throw new AccessDeniedException(ErrorConstants.INVALID_CREDENTIALS, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.LOGIN_ERROR, ex);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> optionalUser = userService.findByEmail(username);
            User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(ErrorConstants.USER_NOT_FOUND + " with email: " + username));

            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

            AuthResponse authResponse = createAuthResponse(user);

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    true, true, true, true,
                    authorities
            );

        } catch (UsernameNotFoundException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private AuthResponse createAuthResponse(User user) {
        try {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(jwtService.generateToken(user.getUsername(), new HashMap<>()));
            authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user.getId()).getToken());

            return authResponse;

        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.AUTH_RESPONSE_ERROR, ex);
        }
    }
}