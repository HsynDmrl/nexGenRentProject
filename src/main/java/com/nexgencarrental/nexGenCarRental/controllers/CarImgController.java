package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.CarImgService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.AddCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg.UpdateCarImgRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg.GetCarImgResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/carimgs")
@AllArgsConstructor
public class CarImgController {
    private final CarImgService carImgService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadCarImage(
            @RequestParam("carId") int carId,
            @RequestParam("image") MultipartFile image) {
            GetCarImgResponse newCarImg = carImgService.uploadCarImage(image, carId);
            return new ResponseEntity<>(newCarImg, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carImgId}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCarImage(
            @PathVariable("carImgId") int carImgId,
            @RequestParam("image") MultipartFile image) {
        GetCarImgResponse updatedCarImg = carImgService.updateCarImage(image, carImgId);
        return ResponseEntity.ok(updatedCarImg);
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
