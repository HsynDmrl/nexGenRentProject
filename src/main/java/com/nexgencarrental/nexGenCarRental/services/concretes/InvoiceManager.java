package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import com.nexgencarrental.nexGenCarRental.repositories.InvoiceRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.InvoiceService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.DeleteInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.invoice.InvoiceBusinessRulesService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceManager extends BaseManager<Invoice, InvoiceRepository, GetInvoiceResponse, GetInvoiceListResponse,
        AddInvoiceRequest, UpdateInvoiceRequest> implements InvoiceService {

    private final InvoiceBusinessRulesService invoiceBusinessRulesService;

    public InvoiceManager(InvoiceRepository repository, ModelMapperService modelMapperService, InvoiceBusinessRulesService invoiceBusinessRulesService) {
        super(repository, modelMapperService, GetInvoiceResponse.class, GetInvoiceListResponse.class, Invoice.class,
                AddInvoiceRequest.class, UpdateInvoiceRequest.class);
        this.invoiceBusinessRulesService = invoiceBusinessRulesService;
    }

    @Override
    public void customAdd(AddInvoiceRequest addInvoiceRequest) {
        Invoice invoice = modelMapperService.forRequest().map(addInvoiceRequest, Invoice.class);
        invoiceBusinessRulesService.validateInvoice(invoice);
        repository.save(invoice);
    }

    @Override
    public void customUpdate(UpdateInvoiceRequest updateInvoiceRequest) {
        Invoice invoice = modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
        invoiceBusinessRulesService.validateInvoice(invoice);
        repository.save(invoice);
    }

    @Override
    public void customDelete(DeleteInvoiceRequest deleteInvoiceRequest) {
        invoiceBusinessRulesService.checkDeleteInvoiceRules(deleteInvoiceRequest.getId());
    }

}

