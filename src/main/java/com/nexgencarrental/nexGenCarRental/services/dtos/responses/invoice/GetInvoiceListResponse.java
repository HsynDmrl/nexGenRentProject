package com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceListResponse extends GetBaseListResponse {
    private String invoiceNo;
    private Float totalPrice;
    private Float discountRate;
    private Float taxRate;
    private GetRentalListResponse rent;
}
