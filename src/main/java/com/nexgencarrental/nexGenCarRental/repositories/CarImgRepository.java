package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.CarImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarImgRepository extends JpaRepository<CarImg, Integer> {
    List<CarImg> findByCarId(int carId);
    void deleteByCarId(int carId);

}
