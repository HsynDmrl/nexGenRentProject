package com.nexgencarrental.nexGenCarRental.services.rules.invoice;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;

public interface InvoiceBusinessRulesService {
    void validateInvoice(Invoice invoice);
}
