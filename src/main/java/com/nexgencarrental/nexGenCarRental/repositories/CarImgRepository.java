package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.CarImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarImgRepository extends JpaRepository<CarImg, Integer> {
    // Burada belirli bir Car'ın tüm resimlerini bulan bir sorgu yazabilirsiniz.
    List<CarImg> findByCarId(int carId);


    // İsteğe bağlı olarak eğer belirli bir aracın tüm resimlerini silmek
    // isterseniz JPA Query Creation özelliğini kullanabilirsiniz.
    void deleteByCarId(int carId);

}
