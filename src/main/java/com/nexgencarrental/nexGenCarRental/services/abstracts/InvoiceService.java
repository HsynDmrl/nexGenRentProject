package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import com.nexgencarrental.nexGenCarRental.repositories.InvoiceRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceResponse;

public interface InvoiceService extends BaseService<Invoice, InvoiceRepository, GetInvoiceResponse,
        GetInvoiceListResponse, AddInvoiceRequest, UpdateInvoiceRequest> {
    void customAdd(AddInvoiceRequest addInvoiceRequest);

    void customUpdate(UpdateInvoiceRequest updateInvoiceRequest);
}
