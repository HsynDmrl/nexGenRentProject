package com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.InvoiceConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInvoiceRequest {

    @NotBlank(message = InvoiceConstants.INVOICE_NO_BLANK)
    @Pattern(regexp = "\\d{10}", message = InvoiceConstants.INVOICE_NO_PATTERN)
    private String invoiceNo;

    @NotNull(message = InvoiceConstants.INVOICE_TOTAL_NULL)
    @Positive(message = InvoiceConstants.INVOICE_TOTAL_POSITIVE)
    private Float totalPrice;

    @NotNull(message = InvoiceConstants.INVOICE_DISCOUNT_NULL)
    @DecimalMin(value = "0.0", message = InvoiceConstants.INVOICE_DECIMAL_DISCOUNT_MIN)
    @DecimalMax(value = "100.0", message = InvoiceConstants.INVOICE_DECIMAL_DISCOUNT_MAX)
    private Float discountRate;

    @NotNull(message = InvoiceConstants.INVOICE_TAX_NULL)
    @DecimalMin(value = "0.0", message = InvoiceConstants.INVOICE_TAX_DISCOUNT_MIN)
    @DecimalMax(value = "100.0", message = InvoiceConstants.INVOICE_TAX_DISCOUNT_MAX)
    private Float taxRate;
}
