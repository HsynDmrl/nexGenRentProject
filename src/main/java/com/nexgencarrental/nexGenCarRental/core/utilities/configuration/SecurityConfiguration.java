package com.nexgencarrental.nexGenCarRental.core.utilities.configuration;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.SecurityConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.filter.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SecurityConstants.WHITE_LIST_URLS).permitAll()
                        .requestMatchers("swagger-ui/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/search/**").permitAll()
                        //USER
                        .requestMatchers(HttpMethod.PUT, "/admin/update/{userId}/{newRoleId}").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/users/**").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/getByEmail").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                        //BRAND
                        .requestMatchers(HttpMethod.POST, "/api/v1/brands/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/brands/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/brands/delete").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/brands/**").permitAll()
                        //.requestMatchers(HttpMethod.GET,"/api/v1/brands/getAll").hasAnyAuthority("ADMIN","USER","MANAGER")

                        //COLOR
                        .requestMatchers(HttpMethod.POST, "/api/v1/colors/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/colors/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/colors/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/colors/**").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/api/v1/colors/getAll}").hasAnyAuthority("ADMIN","USER","MANAGER")

                        //CARIMG
                        .requestMatchers(HttpMethod.POST, "/api/v1/carimgs/upload").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/carimgs/{carImgId}/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/carimgs/{carImgId}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/carimgs/**").permitAll()

                        //CAR
                        .requestMatchers("/api/v1/cars/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cars/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/cars/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/cars/**").permitAll()

                        //MODEL
                        .requestMatchers(HttpMethod.POST, "/api/v1/models/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/models/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/models/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/models/**").permitAll()

                        //INVOICE
                        .requestMatchers(HttpMethod.POST, "/api/v1/invoices/add").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/invoices/update").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/invoices/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/invoices/**").permitAll()

                        //RENTAL
                        .requestMatchers(HttpMethod.POST, "/api/v1/rentals/admin/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/rentals/admin/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/rentals/add").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/rentals/update").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/rentals/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/rentals/**").permitAll()

                        //ROLE
                        .requestMatchers(HttpMethod.POST, "/api/v1/roles/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/roles/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/roles/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/roles/**").hasAuthority("ADMIN")

                        //CUSTOMER
                        .requestMatchers(HttpMethod.POST, "/api/v1/customers/add").hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/customers/update").hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/customers/**").hasAuthority("ADMIN")

                        //EMPLOYEE
                        .requestMatchers(HttpMethod.POST, "/api/v1/employees/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/employees/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/employees/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees/**").hasAuthority("ADMIN")
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}