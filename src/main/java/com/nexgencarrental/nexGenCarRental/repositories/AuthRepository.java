package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Integer> {
}
