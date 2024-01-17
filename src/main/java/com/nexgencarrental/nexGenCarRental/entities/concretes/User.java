package com.nexgencarrental.nexGenCarRental.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="roles")
    @Enumerated(EnumType.STRING)
    private List<Role> authorities;

    @Column(name = "gsm")
    private String gsm;

    @OneToMany(mappedBy = "user")
    private List<Customer> customers;

    @OneToMany(mappedBy = "user")
    private List<Employee> employees;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
