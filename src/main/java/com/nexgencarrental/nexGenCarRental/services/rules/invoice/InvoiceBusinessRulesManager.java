package com.nexgencarrental.nexGenCarRental.services.rules.invoice;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.NO_INVOICE_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.NO_INVOICE_FOUND;

@Service
@AllArgsConstructor
public class InvoiceBusinessRulesManager implements InvoiceBusinessRulesService {
    private final InvoiceRepository invoiceRepository;

    @Override
    public void validateInvoice(Invoice invoice) {
        if (invoiceRepository.existsByInvoiceNo(invoice.getInvoiceNo())) {
            throw new ConflictException(NO_INVOICE_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkDeleteInvoiceRules(int invoiceId) {

        Invoice invoiceToDelete = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new DataNotFoundException(NO_INVOICE_FOUND));

        Rental rental = invoiceToDelete.getRental();
        if (rental != null) {
            rental.setInvoices(null);
        }
        invoiceRepository.save(invoiceToDelete);

        invoiceRepository.delete(invoiceToDelete);
    }
}
