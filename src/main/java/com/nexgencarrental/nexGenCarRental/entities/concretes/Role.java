package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}