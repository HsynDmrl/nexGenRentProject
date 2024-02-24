package com.nexgencarrental.nexGenCarRental.services.rules.carimg;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.CarImg;
import com.nexgencarrental.nexGenCarRental.repositories.CarImgRepository;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarImgBusinessRulesManager implements CarImgBusinessRulesService {
    private final CarRepository carRepository;
    private final CarImgRepository carImgRepository;

    @Override
    @Transactional
    public GetCarImgResponse uploadCarImage(MultipartFile file, int carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found for ID: " + carId));

        CarImg carImg = new CarImg();
        carImg.setCar(car);
        CarImg savedCarImg = carImgRepository.save(carImg);

        return new GetCarImgResponse(savedCarImg.getId(), savedCarImg.getImageUrl(), savedCarImg.getCar().getId(), savedCarImg.getPublicId());
    }

    @Override
    @Transactional
    public GetCarImgResponse updateCarImage(MultipartFile file, int carImgId) {
        CarImg existingCarImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found for ID: " + carImgId));

        CarImg updatedCarImg = carImgRepository.save(existingCarImg);

        return new GetCarImgResponse(updatedCarImg.getId(), updatedCarImg.getImageUrl(), updatedCarImg.getCar().getId(), updatedCarImg.getPublicId());
    }

    @Override
    @Transactional
    public void deleteCarImage(int carImgId) {
        CarImg carImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found with ID: " + carImgId));

        carImgRepository.delete(carImg);
    }

    @Override
    public List<GetCarImgResponse> getCarImagesByCarId(int carId) {

        List<CarImg> carImages = carImgRepository.findByCarId(carId);

        return carImages.stream()
                .map(carImg -> new GetCarImgResponse(
                        carImg.getId(),
                        carImg.getImageUrl(),
                        carImg.getCar().getId(),
                        carImg.getPublicId()))
                .collect(Collectors.toList());
    }
}
