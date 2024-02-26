package com.nexgencarrental.nexGenCarRental.services.rules.carimg;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class CarImgBusinessRulesManager implements CarImgBusinessRulesService {
    private final CarRepository carRepository;
    private final CarImgRepository carImgRepository;
    private final Cloudinary cloudinary;

    @Override
    @Transactional
    @SneakyThrows
    public GetCarImgResponse uploadCarImage(MultipartFile file, int carId) {

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        String imageUrl = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");

        CarImg carImg = new CarImg();
        carImg.setImageUrl(imageUrl);
        carImg.setPublicId(publicId);

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));
        carImg.setCar(car);

        CarImg savedCarImg = carImgRepository.save(carImg);

        car.setImagePath(imageUrl);
        carRepository.save(car);

        return new GetCarImgResponse(savedCarImg.getId(), savedCarImg.getImageUrl(), savedCarImg.getCar().getId(), savedCarImg.getPublicId());
    }

    @Override
    @Transactional
    @SneakyThrows
    public GetCarImgResponse updateCarImage(MultipartFile file, int carImgId) {

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        String imageUrl = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");


        CarImg existingCarImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));


        existingCarImg.setImageUrl(imageUrl);
        existingCarImg.setPublicId(publicId);

        CarImg updatedCarImg = carImgRepository.save(existingCarImg);

        return new GetCarImgResponse(updatedCarImg.getId(), updatedCarImg.getImageUrl(), updatedCarImg.getCar().getId(), updatedCarImg.getPublicId());
    }

    @Override
    @Transactional
    public void deleteCarImage(int carImgId) {
        CarImg carImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));

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
