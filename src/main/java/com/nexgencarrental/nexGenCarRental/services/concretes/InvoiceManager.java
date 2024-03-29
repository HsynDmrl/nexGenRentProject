package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.InvoiceRepository;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.InvoiceService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.invoice.InvoiceBusinessRulesManager;
import com.nexgencarrental.nexGenCarRental.services.rules.invoice.InvoiceBusinessRulesService;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.NO_INVOICE_FOUND;

@Service
public class InvoiceManager extends BaseManager<Invoice, InvoiceRepository, GetInvoiceResponse, GetInvoiceListResponse,
        AddInvoiceRequest, UpdateInvoiceRequest> implements InvoiceService {

    private final InvoiceBusinessRulesService invoiceBusinessRulesService;
    private final InvoiceBusinessRulesManager invoiceBusinessRulesManager;

    public InvoiceManager(InvoiceRepository repository, ModelMapperService modelMapperService, InvoiceBusinessRulesService invoiceBusinessRulesService, InvoiceBusinessRulesManager invoiceBusinessRulesManager, RentalRepository rentalRepository) {
        super(repository, modelMapperService, GetInvoiceResponse.class, GetInvoiceListResponse.class, Invoice.class,
                AddInvoiceRequest.class, UpdateInvoiceRequest.class);
        this.invoiceBusinessRulesService = invoiceBusinessRulesService;
        this.invoiceBusinessRulesManager = invoiceBusinessRulesManager;
    }

    @Override
    public void customAdd(AddInvoiceRequest addInvoiceRequest) {

        Invoice invoice = modelMapperService.forRequest().map(addInvoiceRequest, Invoice.class);

        String invoiceNumber = invoiceBusinessRulesManager.generateInvoiceNumber();
        invoice.setInvoiceNo(invoiceNumber);

        Rental rental = invoiceBusinessRulesManager.getRentalById(addInvoiceRequest.getRentalId());

        invoice.setTotalPrice((float) rental.getTotalPrice());

        invoice.setRental(rental);

        invoiceBusinessRulesService.validateInvoice(invoice);

        repository.save(invoice);
    }

    @Override
    public void customUpdate(UpdateInvoiceRequest updateInvoiceRequest) {
        Invoice existingInvoice = repository.findById(updateInvoiceRequest.getId())
                .orElseThrow(() -> new DataNotFoundException(NO_INVOICE_FOUND));

        Rental rental = existingInvoice.getRental();
        if (rental == null) {
            throw new DataNotFoundException(ENTITY_NOT_FOUND);
        }
        existingInvoice.setTotalPrice((float) rental.getTotalPrice());
        existingInvoice.setDiscountRate(updateInvoiceRequest.getDiscountRate());
        existingInvoice.setTaxRate(updateInvoiceRequest.getTaxRate());

        if (updateInvoiceRequest.getInvoiceNo() != null && !updateInvoiceRequest.getInvoiceNo().isEmpty()) {
            existingInvoice.setInvoiceNo(updateInvoiceRequest.getInvoiceNo());
        }
        repository.save(existingInvoice);
    }

    @Override
    public void customDelete(int id) {
        invoiceBusinessRulesService.checkDeleteInvoiceRules(id);
    }
}

