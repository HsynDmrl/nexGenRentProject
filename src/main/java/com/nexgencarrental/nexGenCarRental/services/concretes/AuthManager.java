package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.InternalServerEnum;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.*;
import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AuthService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.UpdatePasswordRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.auth.AuthBusinessRulesManager;
import com.nexgencarrental.nexGenCarRental.services.rules.auth.AuthBusinessRulesService;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.USER_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.*;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.InternalServerEnum.TOKEN_GENERATION_ERROR;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.UnauthorizedEnum.INVALID_CREDENTIALS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.UnauthorizedEnum.PASSWORD_ERROR;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthBusinessRulesService authBusinessRulesService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegisterRequest request) {

        authBusinessRulesService.validateRegistration(request);
            if (userService.existsByEmail(request.getEmail())) {
                throw new ConflictException(USER_ALREADY_EXISTS);
            }

            Role userRole = userService.findRoleById(request.getRoleId())
                    .orElseThrow(() -> new DataNotFoundException(ROLE_NOT_FOUND));

            String encodedPassword = passwordEncoder.encode(request.getPassword());

            User user = User.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .nationalityId(request.getNationalityId())
                    .gsm(request.getGsm())
                    .email(request.getEmail())
                    .password(encodedPassword)
                    .role(userRole)
                    .build();

            userService.add(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(user.getUsername(), Collections.singletonMap("userId", user.getId()));
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public void updatePasswordForUser(UpdatePasswordRequest updatePasswordRequest) {
        User user = userService.findByEmail(updatePasswordRequest.getEmail())
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));

        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new UnauthorizedException(PASSWORD_ERROR);
        }
        userService.updateUserPassword(updatePasswordRequest);
    }

    private AuthResponse createAuthResponse(User user) {
        AuthResponse authResponse = new AuthResponse();
        try {
            authResponse.setAccessToken(jwtService.generateToken(user.getUsername(), new HashMap<>()));
            authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user.getId()).getToken());
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException(TOKEN_GENERATION_ERROR);
        }
        return authResponse;
    }
}