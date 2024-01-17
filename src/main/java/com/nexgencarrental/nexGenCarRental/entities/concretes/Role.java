package com.nexgencarrental.nexGenCarRental.entities.concretes;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    MODERATOR,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}