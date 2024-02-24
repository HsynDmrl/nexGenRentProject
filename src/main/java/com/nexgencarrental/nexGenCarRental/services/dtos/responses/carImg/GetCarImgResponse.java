package com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarImgResponse {

    private int id;

    private String imagePath;

    private int carId;

    private String publicId;

}
