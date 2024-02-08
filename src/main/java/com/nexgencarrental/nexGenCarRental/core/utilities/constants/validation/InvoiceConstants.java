package com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation;

public class InvoiceConstants {
    public static final String INVOICE_ID_NULL = ("Invoice ID cannot be null");
    public static final String INVOICE_NO_BLANK = ("Invoice number cannot be blank");
    public static final String INVOICE_NO_PATTERN = ("Invalid GSM format. It must be a 10-digit number.");
    public static final String INVOICE_TOTAL_NULL = ("Total price cannot be null");
    public static final String INVOICE_TOTAL_POSITIVE = ("Total price must be a positive number");
    public static final String INVOICE_DISCOUNT_NULL = ("Discount rate cannot be null");
    public static final String INVOICE_DECIMAL_DISCOUNT_MIN = ("Discount rate cannot be negative");
    public static final String INVOICE_DECIMAL_DISCOUNT_MAX = ("Discount rate cannot be greater than 100");
    public static final String INVOICE_TAX_NULL = ("Tax rate cannot be null");
    public static final String INVOICE_TAX_DISCOUNT_MIN = ("Tax rate cannot be negative");
    public static final String INVOICE_TAX_DISCOUNT_MAX = ("Tax rate cannot be greater than 100");

    public static final String ID_POSITIVE = ("Id field cannot be less than 0.");
}
