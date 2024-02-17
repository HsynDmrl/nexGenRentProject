package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.CarImg;
import com.nexgencarrental.nexGenCarRental.repositories.CarImgRepository;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarImgService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.AddCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.UpdateCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarImgManager implements CarImgService {

    private final CarImgRepository carImgRepository;
    private final CarRepository carRepository;
    private final Cloudinary cloudinary;

    @Autowired
    public CarImgManager(CarImgRepository carImgRepository, CarRepository carRepository, Cloudinary cloudinary) {
        this.carImgRepository = carImgRepository;
        this.carRepository = carRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    @Transactional
    public GetCarImgResponse uploadCarImage(MultipartFile file, int carId) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String imageUrl = (String) uploadResult.get("url");
            String publicId = (String) uploadResult.get("public_id");
            CarImg carImg = new CarImg();
            carImg.setImageUrl(imageUrl);
            carImg.setPublicId(publicId);
            Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found for ID: " + carId));
            carImg.setCar(car);
            CarImg savedCarImg = carImgRepository.save(carImg);
            return new GetCarImgResponse(savedCarImg.getId(), savedCarImg.getImageUrl(), savedCarImg.getCar().getId(), savedCarImg.getPublicId());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image to Cloudinary", e);
        }
    }

    @Override
    @Transactional
    public GetCarImgResponse updateCarImage(MultipartFile file, int carImgId) {
        CarImg existingCarImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found for ID: " + carImgId));
        try {
            // Eski resim varsa Cloudinary'den silinmesi
            if (existingCarImg.getPublicId() != null && !existingCarImg.getPublicId().isEmpty()) {
                cloudinary.uploader().destroy(existingCarImg.getPublicId(), ObjectUtils.emptyMap());
            }
            // Yeni resmi Cloudinary'ye yükleyin ve sonuçları alın
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String newImageUrl = (String) uploadResult.get("url");
            String newPublicId = (String) uploadResult.get("public_id");

            // ExistingCarImg objesini yeni URL ve publicId ile güncelleyin
            existingCarImg.setImageUrl(newImageUrl);
            existingCarImg.setPublicId(newPublicId);

            // Veritabanında bu değişiklikleri güncelleyin
            CarImg updatedCarImg = carImgRepository.save(existingCarImg);

            // Güncellenmiş resim bilgisini içeren DTO'yu dönün
            return new GetCarImgResponse(
                    updatedCarImg.getId(),
                    updatedCarImg.getImageUrl(),
                    updatedCarImg.getCar().getId(),
                    updatedCarImg.getPublicId()); // Eğer publicId field'ı DTO'nuzda yer alıyorsa eklenmelidir.
        } catch (IOException e) {
            throw new RuntimeException("Cloudinary image update failed", e);
        }
    }

    @Override
    @Transactional
    public void deleteCarImage(int carImgId) {
        CarImg carImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found with ID: " + carImgId));
        try {
            if (carImg.getPublicId() != null && !carImg.getPublicId().isEmpty()) {
                cloudinary.uploader().destroy(carImg.getPublicId(), ObjectUtils.emptyMap());
            }
            carImgRepository.delete(carImg);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image from Cloudinary", e);
        }
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