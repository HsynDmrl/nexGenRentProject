package com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceResponse extends GetBaseResponse {
    private String invoiceNo;
    private Float totalPrice;
    private Float discountRate;
    private Float taxRate;
    private GetRentalResponse rent;
}
