package com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.CarImgConstants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarImgRequest {
    @NotBlank(message = CarImgConstants.CARIMG_NOT_BLANK)
    @NotNull(message = CarImgConstants.CARIMG_NOT_NULL)
    private MultipartFile file;

    @NotNull(message = CarImgConstants.CARIMG_NOT_ID)
    private int carId;
}
