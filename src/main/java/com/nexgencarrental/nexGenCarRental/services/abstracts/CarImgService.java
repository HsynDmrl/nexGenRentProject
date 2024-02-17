package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.CarImg;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.AddCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.UpdateCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarImgService {
    GetCarImgResponse uploadCarImage(MultipartFile file, int carId) throws IOException;
    GetCarImgResponse updateCarImage(MultipartFile file, int carImgId) throws IOException;
    void deleteCarImage(int carImgId);
    List<GetCarImgResponse> getCarImagesByCarId(int carId);
}