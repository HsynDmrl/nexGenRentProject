package com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInvoiceRequest {

    private String invoiceNo;

    private Float totalPrice;

    private Float discountRate;

    private Float taxRate;
}
