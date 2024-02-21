package com.nexgencarrental.nexGenCarRental.services.rules.invoice;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.InvoiceRepository;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.*;

@Service
@AllArgsConstructor
public class InvoiceBusinessRulesManager implements InvoiceBusinessRulesService {
    private final InvoiceRepository invoiceRepository;
    private final RentalRepository rentalRepository;

    @Override
    public void validateInvoice(Invoice invoice) {
        if (invoiceRepository.existsByInvoiceNo(invoice.getInvoiceNo())) {
            throw new DataNotFoundException(INVOICE_NOT_FOUND);
        }
    }

    public Rental getRentalById(int rentalId) {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND_WITH_ID));
    }

    public String generateInvoiceNumber() {

        Random random = new Random();
        StringBuilder invoiceNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            invoiceNumber.append(random.nextInt(9));
        }
        return invoiceNumber.toString();
    }

    @Override
    public void checkDeleteInvoiceRules(int invoiceId) {

        Invoice invoiceToDelete = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new DataNotFoundException(NO_INVOICE_FOUND));

        Rental rental = invoiceToDelete.getRental();
        if (rental != null) {
            rental.getInvoices().remove(invoiceToDelete);
            rentalRepository.save(rental);
        }

        invoiceRepository.save(invoiceToDelete);

        invoiceRepository.delete(invoiceToDelete);
    }
}
