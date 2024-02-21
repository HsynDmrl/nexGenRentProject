package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.CarImgService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.AddCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.UpdateCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController // REST Controller olduğunu bildirmek için
@RequestMapping("/api/carimgs") // Bu controller'ın kök URL yolu
public class CarImgController {
    private final CarImgService carImgService;

    @Autowired
    public CarImgController(CarImgService carImgService) {
        this.carImgService = carImgService;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadCarImage(
            @RequestParam("carId") int carId,
            @RequestParam("image") MultipartFile image) {
        try {
            GetCarImgResponse newCarImg = carImgService.uploadCarImage(image, carId);
            return new ResponseEntity<>(newCarImg, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image: " + e.getMessage());
        }
    }

    @PutMapping("/{carImgId}")
    public ResponseEntity<?> updateCarImage(
            @PathVariable("carImgId") int carImgId,
            @RequestParam("image") MultipartFile image) {
        try {
            GetCarImgResponse updatedCarImg = carImgService.updateCarImage(image, carImgId);
            return ResponseEntity.ok(updatedCarImg);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating image: " + e.getMessage());
        }
    }

    @DeleteMapping("/{carImgId}")
    public ResponseEntity<Void> deleteCarImage(@PathVariable("carImgId") int carImgId) {
        carImgService.deleteCarImage(carImgId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<GetCarImgResponse>> getAllImagesByCarId(@PathVariable int carId) {
        List<GetCarImgResponse> carImgList = carImgService.getCarImagesByCarId(carId);
        return ResponseEntity.ok(carImgList);
    }
}
