package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
