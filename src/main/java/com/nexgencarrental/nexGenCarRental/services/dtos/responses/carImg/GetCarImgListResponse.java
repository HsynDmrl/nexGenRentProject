package com.nexgencarrental.nexGenCarRental.services.dtos.responses.carImg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarImgListResponse {
    private List<GetCarImgResponse> carImgResponses; // Resimler listesi
    private int currentPage;                           // Geçerli sayfa numarası
    private int totalItems;                            // Toplam öğe sayısı
    private int totalPages;
}
