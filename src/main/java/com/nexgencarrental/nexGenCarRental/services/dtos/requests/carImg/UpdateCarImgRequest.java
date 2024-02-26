package com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.CarImgConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImgRequest {
    @Positive(message = CarImgConstants.CARIMG_ID_POSITIVE)
    private int id;

    @NotNull(message = CarImgConstants.CARIMG_NOT_NULL)
    private MultipartFile file;
}
