package com.nexgencarrental.nexGenCarRental.services.dtos.requests.carImg;

import jakarta.validation.constraints.NotBlank;
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
    @Positive(message = "ID must be positive")
    private int id;

    @NotBlank(message = "Public ID must not be blank")
    private String publicId; // Cloudinary tarafından verilen resmin unique public ID'si

    @NotNull(message = "File must not be null")
    private MultipartFile file; // Yeni resim dosyası

    // Diğer gerekebilecek alanlar...

    // Lombok @Data zaten constructor, getter, ve setter metotlarını sağlar.

}
