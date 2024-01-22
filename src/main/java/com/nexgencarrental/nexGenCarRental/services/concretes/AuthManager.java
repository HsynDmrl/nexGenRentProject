package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AuthService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.rules.user.UserBusinessRulesService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserBusinessRulesService userBusinessRulesService;
    private final UserRepository userRepository;

    @Override
    public void register(CreateUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        userBusinessRulesService.existsByName(request.getEmail());

        Role role = new Role();
        role.setId(request.getRoleId());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(role);
        user.setPassword(request.getPassword());

        userRepository.save(user);
    }


    @Override
    public String login(LoginRequest request) {
        return "";
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
                authorities
        );

    }
}
