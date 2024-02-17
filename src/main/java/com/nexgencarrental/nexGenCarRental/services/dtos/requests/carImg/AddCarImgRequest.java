package com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg;

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
    @NotBlank(message = "Image path cannot be blank")
    @NotNull(message = "File cannot be null")
    private MultipartFile file;

    @NotNull(message = "Car ID cannot be null")
    private int carId; // Araba ID'si

    // Cloudinary'den veya yükleme işleminden sonraki URL ve public ID'i tutmak için alanlar gerekirse buraya eklenebilir

    // Constructor, getters ve setters
    // Lombok kullanıyorsanız, @Data otomatik olarak bu metodları sağlayacaktır.
}
