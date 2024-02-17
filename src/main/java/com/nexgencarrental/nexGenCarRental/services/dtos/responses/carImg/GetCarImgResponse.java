package com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarImgResponse {
    private int id;          // İmage ID'si
    private String imagePath; // İmage yolu
    private int carId;       // Araba ID'si
    // Eğer Cloudinary'den gelen bir publicId değeri döndürmek isterseniz ekleyebilirsiniz.
    private String publicId; // Resmin Cloudinary'deki uniq public ID'si
}
