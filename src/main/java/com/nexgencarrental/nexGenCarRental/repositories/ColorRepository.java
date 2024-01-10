package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    boolean existsByName(String name);
}
