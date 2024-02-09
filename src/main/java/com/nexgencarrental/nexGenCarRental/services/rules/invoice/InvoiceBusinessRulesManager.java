package com.nexgencarrental.nexGenCarRental.services.rules.invoice;

import com.nexgencarrental.nexGenCarRental.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRulesManager implements InvoiceBusinessRulesService {
    private InvoiceRepository invoiceRepository;
}
