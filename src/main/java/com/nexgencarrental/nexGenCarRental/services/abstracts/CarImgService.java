package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarImgService {
    GetCarImgResponse uploadCarImage(MultipartFile file, int carId);

    GetCarImgResponse updateCarImage(MultipartFile file, int carImgId);

    void deleteCarImage(int carImgId);

    List<GetCarImgResponse> getCarImagesByCarId(int carId);
}
