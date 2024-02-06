package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AuthService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.rules.user.UserBusinessRulesService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserBusinessRulesService userBusinessRulesService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void register(CreateUserRequest request) {
        userBusinessRulesService.existsByName(request.getEmail());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Role role = new Role();
        role.setId(request.getRoleId());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(role);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    @Override
    public Map<String, String> login(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());

        String accessToken = jwtService.generateToken(email, claims);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken.getToken());

        return tokens;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true, true, true, true,
                authorities
        );
    }
}