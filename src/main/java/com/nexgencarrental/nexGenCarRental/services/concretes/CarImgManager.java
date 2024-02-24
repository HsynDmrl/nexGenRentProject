package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nexgencarrental.nexGenCarRental.entities.concretes.CarImg;
import com.nexgencarrental.nexGenCarRental.repositories.CarImgRepository;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarImgService;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.carimg.CarImgBusinessRulesService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarImgManager implements CarImgService {
    private final CarImgBusinessRulesService carImgBusinessRulesService;
    private final Cloudinary cloudinary;

    @Override
    @Transactional
    @SneakyThrows
    public GetCarImgResponse uploadCarImage(MultipartFile file, int carId) {

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        String imageUrl = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");

        return carImgBusinessRulesService.uploadCarImage(file, carId);
    }

    @Override
    @Transactional
    @SneakyThrows
    public GetCarImgResponse updateCarImage(MultipartFile file, int carImgId){

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        String imageUrl = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");

        return carImgBusinessRulesService.updateCarImage(file, carImgId);
    }

    @Override
    @Transactional
    public void deleteCarImage(int carImgId) {
        carImgBusinessRulesService.deleteCarImage(carImgId);
    }

    @Override
    public List<GetCarImgResponse> getCarImagesByCarId(int carId) {
        return carImgBusinessRulesService.getCarImagesByCarId(carId);
    }
}