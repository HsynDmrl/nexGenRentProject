package com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInvoiceRequest {

    @NotBlank(message = ErrorConstants.INVOICE_NO_BLANK)
    @Pattern(regexp = "\\d{10}", message = ErrorConstants.INVOICE_NO_PATTERN)
    private String invoiceNo;

    @NotNull(message = ErrorConstants.INVOICE_TOTAL_NULL)
    @Positive(message = ErrorConstants.INVOICE_TOTAL_POSITIVE)
    private Float totalPrice;

    @NotNull(message = ErrorConstants.INVOICE_DISCOUNT_NULL)
    @DecimalMin(value = "0.0", message = ErrorConstants.INVOICE_DECIMAL_DISCOUNT_MIN )
    @DecimalMax(value = "100.0", message = ErrorConstants.INVOICE_DECIMAL_DISCOUNT_MAX)
    private Float discountRate;

    @NotNull(message = ErrorConstants.INVOICE_TAX_NULL)
    @DecimalMin(value = "0.0", message = ErrorConstants.INVOICE_TAX_DISCOUNT_MIN)
    @DecimalMax(value = "100.0", message = ErrorConstants.INVOICE_TAX_DISCOUNT_MAX)
    private Float taxRate;
}
