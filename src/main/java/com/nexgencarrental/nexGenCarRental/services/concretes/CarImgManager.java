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
    public GetCarImgResponse uploadCarImage(MultipartFile file, int carId) throws IOException {
        // Cloudinary'e dosyayı yükleme
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        // Yükleme sonucundan resim URL'sini ve public ID'yi alın
        String imageUrl = (String) uploadResult.get("url");
        String publicId = (String) uploadResult.get("public_id");

        // CarImg nesnesi oluşturma ve bilgileri ayarlama
        CarImg carImg = new CarImg();
        carImg.setImageUrl(imageUrl);
        carImg.setPublicId(publicId);

        // İlgili aracı bulma
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found for ID: " + carId));
        carImg.setCar(car); // CarImg nesnesine aracı ilişkilendirme

        // CarImg nesnesini kaydetme
        CarImg savedCarImg = carImgRepository.save(carImg);

        // Kaydedilen CarImg nesnesiyle bir GetCarImgResponse nesnesi oluşturma ve döndürme
        return new GetCarImgResponse(savedCarImg.getId(), savedCarImg.getImageUrl(), savedCarImg.getCar().getId(), savedCarImg.getPublicId());
    }

    // Resmi güncelleme metodu
    @Override
    @Transactional
    public GetCarImgResponse updateCarImage(MultipartFile file, int carImgId) throws IOException {
        CarImg existingCarImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found for ID: " + carImgId));

        // Eski resmi Cloudinary'den sil
        if (existingCarImg.getPublicId() != null) {
            cloudinary.uploader().destroy(existingCarImg.getPublicId(), ObjectUtils.emptyMap());
        }

        // Yeni resmi Cloudinary'ye yükle ve sonuçları al
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        String newImageUrl = (String) uploadResult.get("url");
        String newPublicId = (String) uploadResult.get("public_id");

        // CarImg nesnesini yeni URL ve publicId ile güncelle
        existingCarImg.setImageUrl(newImageUrl);
        existingCarImg.setPublicId(newPublicId);
        CarImg updatedCarImg = carImgRepository.save(existingCarImg);

        // Güncellenmiş CarImg nesnesi ile bir GetCarImgResponse dön
        return new GetCarImgResponse(updatedCarImg.getId(), updatedCarImg.getImageUrl(), updatedCarImg.getCar().getId(), updatedCarImg.getPublicId());
    }


    // Resmi silme metodu
    @Override
    @Transactional
    public void deleteCarImage(int carImgId) {
        CarImg carImg = carImgRepository.findById(carImgId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found with ID: " + carImgId));

        // Cloudinary'den resmi sil
        if (carImg.getPublicId() != null) {
            try {
                cloudinary.uploader().destroy(carImg.getPublicId(), ObjectUtils.emptyMap());
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image from Cloudinary", e);
            }
        }

        // Veritabanından CarImg nesnesini sil
        carImgRepository.delete(carImg);
    }

    @Override
    public List<GetCarImgResponse> getCarImagesByCarId(int carId) {
        List<CarImg> carImages = carImgRepository.findByCarId(carId); // Veritabanından belirli bir carId için tüm CarImg'leri al
        return carImages.stream()
                .map(carImg -> new GetCarImgResponse(
                        carImg.getId(),
                        carImg.getImageUrl(),
                        carImg.getCar().getId(),
                        carImg.getPublicId()))
                .collect(Collectors.toList()); // CarImg entity'lerini GetCarImgResponse DTO'larına dönüştür ve liste olarak dön
    }
}