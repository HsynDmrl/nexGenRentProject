package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import com.nexgencarrental.nexGenCarRental.repositories.InvoiceRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.InvoiceService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceResponse;
import org.springframework.stereotype.Service;

@Service
public class InvoiceManager extends BaseManager<Invoice, InvoiceRepository, GetInvoiceResponse, GetInvoiceListResponse,
        AddInvoiceRequest, UpdateInvoiceRequest> implements InvoiceService {
    public InvoiceManager(InvoiceRepository repository, ModelMapperService modelMapperService) {
        super(repository, modelMapperService, GetInvoiceResponse.class, GetInvoiceListResponse.class, Invoice.class,
                AddInvoiceRequest.class, UpdateInvoiceRequest.class);
    }
}